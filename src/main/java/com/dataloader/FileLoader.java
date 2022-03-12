package com.dataloader;

import com.anonymizer.AESAnonymizer;
import com.model.ProcessHistory;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class FileLoader {
    private final String secretKey = "MaGiStErKa";
    private List<ProcessHistory> historyList;

    public FileLoader() {
        historyList = new ArrayList<>();
    }

    public void loadFile() throws IOException {
        try {
            String fileName = "input_data/historyProcess.csv";
            historyList = new CsvToBeanBuilder(new FileReader(getClass().getClassLoader().getResource(fileName).getPath()))
                    .withType(ProcessHistory.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IOException();
        }
    }

    public void anonymizeData() {
        Set<String> userSet = new HashSet<>();
        historyList.forEach(event -> {
            String user = event.getStartUser();
            if (user.length() == 0 ){
                user = "unknown";
            }
            userSet.add(user);
        });
        Map<String, String> userMap = new HashMap<>();
        userSet.iterator().forEachRemaining(user -> {
            userMap.put(user, AESAnonymizer.encrypt(user, secretKey));
        });
        userMap.forEach((key, value) -> System.out.println(key + " || " + value));
    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
}
