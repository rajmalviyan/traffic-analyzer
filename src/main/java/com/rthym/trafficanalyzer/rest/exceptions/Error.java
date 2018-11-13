package com.rthym.trafficanalyzer.rest.exceptions;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;


class Error {

    private final int status;

    private final String message;

    private List<FieldError> fieldErrors = new ArrayList<>();

    Error(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public void addFieldError(String objectName, String path, String message) {
        FieldError error = new FieldError(objectName, path, message);
        fieldErrors.add(error);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
