package com.example.myapplication_big_assigment_2.entity;

//用户信息
public class User {
    private int id;

    public User() {
    }

    private String account;
    private String password;
    private String gender;
    private String name;
    private String picture;
    private String description;


    public User(int id, String account, String password, String gender, String name, String picture, String description) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.gender = gender;
        this.name = name;
        this.picture = picture;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}