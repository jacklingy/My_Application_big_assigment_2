package com.example.myapplication_big_assigment_2.entity;

/**
 * 消息类
 */

public class Message {

    public Message(){}
    public static final int TYPE_RECEIVED=1;
    public static final int TYPE_SENT=0;

    private String content;//消息内容
    private int me_id;
    private int him_id;
    //private int position;
    private  int type;//0-接收，1发送

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMe_id() {
        return me_id;
    }

    public void setMe_id(int me_id) {
        this.me_id = me_id;
    }

    public int getHim_id() {
        return him_id;
    }

    public void setHim_id(int him_id) {
        this.him_id = him_id;
    }

    public Message(String content, int me_id, int him_id, int type) {
        this.content = content;
        this.me_id = me_id;
        this.him_id = him_id;
        this.type = type;
    }
}
