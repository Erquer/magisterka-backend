package com.controllers;

import com.services.FileAnonymizerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping(value = "/anonymize")
public class FileAnonymizerController {
    private final FileAnonymizerService fileAnonymizerService;

    public FileAnonymizerController(FileAnonymizerService fileAnonymizerService) {
        this.fileAnonymizerService = fileAnonymizerService;
    }

    @GetMapping("/process")
    public ResponseEntity<String> anonymizeProcess() {
        try {
            fileAnonymizerService.anonymizeProcessHistory();
            return ResponseEntity.ok("done");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error");
        }
    }

}
