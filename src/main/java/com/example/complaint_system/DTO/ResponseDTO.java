package com.example.complaint_system.DTO;

public class ResponseDTO<T> {

    private ResponseHeadDTO head;
    private T body;


    public ResponseDTO(){

    }

    public ResponseDTO(ResponseHeadDTO head , T body){
        this.head = head;
        this.body = body;
    }

    public ResponseHeadDTO getHead() {
        return head;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public T getBody() {
        return body;
    }

    public void setHead(ResponseHeadDTO head) {
        this.head = head;
    }

}
