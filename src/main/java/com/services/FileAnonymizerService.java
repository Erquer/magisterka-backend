package com.services;

import org.springframework.stereotype.Service;

@Service
public interface FileAnonymizerService {
     void anonymizeProcessHistory();
     void anonymizeProcessTasks();
}
