package com.apirest.api.dto;

import java.time.Instant;
import java.util.List;

public class CustomError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
    private List<String> messages;

    public CustomError(Instant timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public CustomError(Instant timestamp, Integer status, String error, String message, String path, List<String> messages) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.messages = messages;
    }

    public Instant getTimestamp() { return timestamp; }
    public Integer getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getPath() { return path; }
    public List<String> getMessages() { return messages; }
}
