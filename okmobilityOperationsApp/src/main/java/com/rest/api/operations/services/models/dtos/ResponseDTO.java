package com.rest.api.operations.services.models.dtos;

// class where the response DTO of the operation is request for users login

public class ResponseDTO {
    private int numOfErrors;
    private String message;

    public int getNumOfErrors() {
        return numOfErrors;
    }

    public void setNumOfErrors(int numOfErrors) {
        this.numOfErrors = numOfErrors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
