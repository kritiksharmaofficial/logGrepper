package com.interview.demo.controller;

import com.interview.demo.constants.SearchLogsRequestParams;
import com.interview.demo.entities.LogSearchRequest;
import com.interview.demo.interfaces.GetSearchLogsController;
import com.interview.demo.service.LogGrepperService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LogSearchController implements GetSearchLogsController {

    LogGrepperService logGrepperService;

    public LogSearchController(LogGrepperService logGrepperService) {
        this.logGrepperService = logGrepperService;
    }

    @Override
    public List<String> searchLogs(@RequestParam(SearchLogsRequestParams.SEARCH_KEYWORD) String query,
                                   @RequestParam(SearchLogsRequestParams.FROM_TIME) String from,
                                   @RequestParam(SearchLogsRequestParams.TO_TIME) String to) throws RuntimeException{
        LogSearchRequest logSearchRequest = new LogSearchRequest();
        logSearchRequest.setSearchKeyword(query);
        logSearchRequest.setFrom(from);
        logSearchRequest.setTo(to);
        return logGrepperService.getLogs(logSearchRequest);
    }
}
