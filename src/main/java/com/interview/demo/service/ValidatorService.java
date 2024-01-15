package com.interview.demo.service;

import com.interview.demo.entities.LogSearchRequest;
import com.interview.demo.interfaces.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidatorService {

    List<Validator> validators;

//    @Autowired
    public ValidatorService(List<Validator> validators) {
        this.validators = validators;
    }
    public void execute(LogSearchRequest logSearchRequest) throws IllegalArgumentException {
        for(Validator validator: validators) {
            validator.validate(logSearchRequest);
        }
    }
}
