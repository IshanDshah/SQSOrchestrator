package com.sqs.orchestrator;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.http.HttpStatus;


public class ResponsePayload {

    private HttpStatus status;
    private String message;

    public ResponsePayload(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
