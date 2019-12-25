package com.example.myapplication_big_assigment_2.entity;

import java.util.ArrayList;


public class MessageReceivedList {

    public MessageReceivedList(){}

    public MessageReceivedList(int code, ArrayList<SimpleMessage> messages) {
        this.code = code;
        this.messages = messages;
    }

    private int code;
    private ArrayList<SimpleMessage> messages;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<SimpleMessage> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<SimpleMessage> messages) {
        this.messages = messages;
    }
}
