package com.services;


import com.dataloader.FileLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FileAnonymizerService {

    public boolean anonymizeProcessHistory() throws IOException {
        FileLoader fileLoader = new FileLoader();
        fileLoader.loadFile();
        fileLoader.anonymizeData();
        return true;
    }
}
