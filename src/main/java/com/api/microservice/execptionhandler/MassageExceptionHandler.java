package com.api.microservice.execptionhandler;

import javax.xml.crypto.Data;

public class MassageExceptionHandler {

    private Data timestamp;
    private Integer status;
    private String message;

    public MassageExceptionHandler(Data timestamp, Integer status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    };

    public Data getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Data timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
