package com.services;


import com.anonymizer.AESAnonymizer;
import com.dataloader.FileLoader;
import com.model.ProcessHistory;
import com.model.ProcessTask;
import com.model.ProcessType;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class FileAnonymizerServiceImpl implements FileAnonymizerService {

    private final FileLoader fileLoader;
    private List<ProcessTask> taskList = new ArrayList<>();
    private List<ProcessHistory> processHistories = new ArrayList<>();

    public FileAnonymizerServiceImpl() {
        fileLoader = new FileLoader();
    }

    @Override
    public void anonymizeProcessHistory() {
        System.out.println("AnonymizeProcessHistory");
        System.out.println("Load File");
        processHistories = (List<ProcessHistory>) fileLoader.loadFile(ProcessType.PROCESS);

        System.out.println("Load File Finished");
        Map<String,String> usernames = this.getAnonymizedUsernames(ProcessType.PROCESS);
        System.out.println("Anonymize usernames");
        processHistories.forEach(event -> {
            String anonymizedUser = event.getStartUser().length() == 0 ? usernames.get("unknown") : usernames.get(event.getStartUser());
            event.setStartUser(anonymizedUser);
        });
        processHistories.forEach(System.out::println);
        fileLoader.saveToFile(processHistories,ProcessType.PROCESS);
    }

    @Override
    public void anonymizeProcessTasks() {
        taskList = (List<ProcessTask>) fileLoader.loadFile(ProcessType.TASK);
        Map<String,String> usernames = this.getAnonymizedUsernames(ProcessType.TASK);
        taskList.forEach(event -> {
            String anonymizedUser = event.getAssignee().length() == 0 ? usernames.get("unknown") : usernames.get(event.getAssignee());
            event.setAssignee(anonymizedUser);
        });
        taskList.forEach(System.out::println);
        fileLoader.saveToFile(taskList, ProcessType.TASK);
    }

    private Map<String,String> getAnonymizedUsernames(ProcessType processType) {
        System.out.println("Anonymizing start");
        String secretKey = getSecretKey();
        Set<String> userSet = new HashSet<>();
        switch (processType) {
            case TASK:
                taskList.forEach(task -> {
                    String assignee = task.getAssignee();
                    if (assignee.length() == 0) {
                        assignee = "unknown";
                    }
                    userSet.add(assignee);
                });
                break;
            case PROCESS:
                processHistories.forEach(event -> {
                    String user = event.getStartUser();
                    if (user.length() == 0) {
                        user = "unknown";
                    }
                    userSet.add(user);
                });
                break;
            default:
                System.out.println("Bad Type");
        }
        Map<String, String> userMap = new HashMap<>();
        userSet.iterator().forEachRemaining(user -> {
            userMap.put(user, AESAnonymizer.encrypt(user, secretKey));
        });
        System.out.println("Anonymizing Finished");
        userMap.forEach((key, value) -> System.out.println(key + " || " + value));
        return  userMap;
    }

    private String getSecretKey() {
        try (InputStream is = Objects.requireNonNull(getClass().getClassLoader().getResource("secret_key.txt")).openStream();
        ) {
            assert is != null;
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader br = new BufferedReader(isr);)
            {
                String line;
                if( (line = br.readLine()) != null) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "DefaultKey";
    }
}
