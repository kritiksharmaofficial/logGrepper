package com.interview.demo.validatorsImplementation;

import com.interview.demo.entities.LogSearchRequest;
import com.interview.demo.interfaces.Validator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/* validates to and from whether they are in format 2011-12-03T10:15:30 without zone
*/
@Component
public class DateValidation implements Validator {

    @Override
    public void validate(LogSearchRequest logSearchRequest) throws IllegalArgumentException {
        try {
            LocalDateTime start = LocalDateTime.parse(logSearchRequest.getFrom());
            LocalDateTime end = LocalDateTime.parse(logSearchRequest.getTo());
            if(end.isBefore(start)) {
                throw new IllegalArgumentException("start time should be less than end time");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Check start and end time format");
        }
    }
}
