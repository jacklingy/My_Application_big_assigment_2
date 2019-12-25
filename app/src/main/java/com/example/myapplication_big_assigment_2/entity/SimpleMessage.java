package com.example.myapplication_big_assigment_2.entity;



public class SimpleMessage {

    public SimpleMessage(){}
    public SimpleMessage(String content, String time) {
        this.content = content;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String content;
    private String time;

}
