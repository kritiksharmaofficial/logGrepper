package com.interview.demo.service;

import com.interview.demo.entities.LogSearchRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogGrepperService {

    ValidatorService validatorService;

    DataCollectorAndProcessingExecutor dataCollectorAndProcessingExecutor;

    public LogGrepperService(ValidatorService validatorService, DataCollectorAndProcessingExecutor dataProcessingService) {
        this.validatorService = validatorService;
        this.dataCollectorAndProcessingExecutor = dataProcessingService;
    }

    public List<String> getLogs(LogSearchRequest logSearchRequest) throws RuntimeException {
        validatorService.execute(logSearchRequest);
        return dataCollectorAndProcessingExecutor.execute(logSearchRequest);
    }

}
