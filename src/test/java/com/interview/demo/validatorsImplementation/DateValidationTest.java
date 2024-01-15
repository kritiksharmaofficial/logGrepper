package com.interview.demo.validatorsImplementation;

import com.interview.demo.entities.LogSearchRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DateValidationTest {

    @Test
    public void testValidate_validDates() {
        LogSearchRequest request = new LogSearchRequest();
        request.setFrom("2023-12-31T23:00");
        request.setTo("2024-01-01T01:00");
        DateValidation validator = new DateValidation();
        validator.validate(request);
    }

    @Test
    public void testValidate_invalidDateFormat() throws IllegalArgumentException {
        LogSearchRequest request = new LogSearchRequest();
        request.setTo("2023-12-31T23:00+5:30");
        request.setFrom("2023/12/31T23:00+5:30");
        DateValidation validator = new DateValidation();
        assertThrows(IllegalArgumentException.class, () -> validator.validate(request));
    }

    @Test
    public void testValidate_endTimeBeforeStartTime() throws IllegalArgumentException {
        LogSearchRequest request = new LogSearchRequest();
        request.setTo("2023-12-31T22:00");
        request.setFrom("2023-12-31T23:00");
        DateValidation validator = new DateValidation();
        assertThrows(IllegalArgumentException.class, () -> validator.validate(request));
    }
}