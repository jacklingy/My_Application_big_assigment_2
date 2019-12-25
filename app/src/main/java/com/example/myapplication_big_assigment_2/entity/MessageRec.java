package com.example.myapplication_big_assigment_2.entity;


/**
 * 接受消息定义的类
 */

public class MessageRec {

    private String content;
    private String time;
    int state;
    public  MessageRec(){}

    public MessageRec(String content, String time, int state) {
        this.content = content;
        this.time = time;
        this.state = state;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

