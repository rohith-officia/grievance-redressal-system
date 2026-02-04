package com.example.complaint_system.DTO;

import java.time.LocalDateTime;

public class ResponseHeadDTO {

    private String status;
    private int code;
    private String message;
    private LocalDateTime timestamp;

    public ResponseHeadDTO(String status , int statusCode , String message){
        this.status = status;
        this.code = statusCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
