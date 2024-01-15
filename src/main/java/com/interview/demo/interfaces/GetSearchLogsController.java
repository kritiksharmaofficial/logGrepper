package com.interview.demo.interfaces;

import com.interview.demo.constants.SearchLogsRequestParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/default")
public interface GetSearchLogsController {

    @GetMapping("/searchLogs")
    List<String> searchLogs(@RequestParam(SearchLogsRequestParams.SEARCH_KEYWORD) String query,
                                           @RequestParam(SearchLogsRequestParams.FROM_TIME) String from,
                                           @RequestParam(SearchLogsRequestParams.TO_TIME) String to);
}
