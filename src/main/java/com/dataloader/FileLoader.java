package com.dataloader;

import com.anonymizer.AESAnonymizer;
import com.model.ProcessHistory;
import com.model.ProcessTask;
import com.model.ProcessType;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class FileLoader {

    public  List<?> loadFile(ProcessType type) {
        try {
            if (Objects.equals(type, ProcessType.PROCESS)) {
                String fileName = "input_data/historyProcess.csv";
                String path = Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();
                return new CsvToBeanBuilder<ProcessHistory>(new FileReader(path))
                        .withType(ProcessHistory.class).build().parse();
            } else if (Objects.equals(type, ProcessType.TASK)) {
                String fileName = "input_data/historyTask.csv";
                String path = Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();
                return new CsvToBeanBuilder<ProcessTask>(new FileReader(path))
                        .withType(ProcessTask.class).build().parse();
            } else {
                System.out.println("Bad type provided");
                return null;
            }
        } catch (FileNotFoundException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveToFile(List<?> eventsList, ProcessType type) {
        String fileName = type == ProcessType.PROCESS ? "ProcessHistoryAnonimized.csv" : "ProcesTasksAnonimized.csv";
        try(Writer writer = new FileWriter("output_files/"+fileName)) {
            StatefulBeanToCsv statefulBeanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();
            statefulBeanToCsv.write(eventsList);
            System.out.println("WRITING TO FILE FINISHED");
        } catch (IOException| CsvRequiredFieldEmptyException| CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }
}
