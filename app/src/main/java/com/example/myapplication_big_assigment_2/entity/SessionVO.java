package com.example.myapplication_big_assigment_2.entity;


//显示在消息列表的会话项目
public class SessionVO {
    private int sessionId;//会话id
    private int talkerId; //会话对象的ID
    private User user;
    private String sessionContent;//最近的一条会话内容
    private String time;//最近一次会话的时间（可以不用显示时间）
    private int userId;//会话的本人;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getTalkerId() {
        return talkerId;
    }

    public void setTalkerId(int talkerId) {
        this.talkerId = talkerId;
    }

    public String getSessionContent() {
        return sessionContent;
    }

    public void setSessionContent(String sessionContent) {
        this.sessionContent = sessionContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public SessionVO(int sessionId, int talkerId, User user, String sessionContent, String time, int userId) {
        this.sessionId = sessionId;
        this.talkerId = talkerId;
        this.user = user;
        this.sessionContent = sessionContent;
        this.time = time;
        this.userId = userId;
    }
    public SessionVO(){}

}