package com.interview.demo.interfaces;

import com.interview.demo.entities.LogSearchRequest;

public interface Validator {

    public void validate(LogSearchRequest logSearchRequest) throws IllegalArgumentException;
}
