package com.controllers;

import com.services.FileAnonymizerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping(value = "/anonymize")
public class FileAnonymizerController {
    private final FileAnonymizerServiceImpl fileAnonymizerService;

    public FileAnonymizerController(FileAnonymizerServiceImpl fileAnonymizerService) {
        this.fileAnonymizerService = fileAnonymizerService;
    }

    @GetMapping("/process")
    public ResponseEntity<String> anonymizeProcess() {
        try {
            fileAnonymizerService.anonymizeProcessHistory();
            return ResponseEntity.ok("Process Anonymized");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error");
        }
    }

    @GetMapping("/tasks")
    public  ResponseEntity<String> anonymizeTasks() {
        try {
            fileAnonymizerService.anonymizeProcessTasks();
            return ResponseEntity.ok("Tasks Anonymized");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error");
        }
    }

}
