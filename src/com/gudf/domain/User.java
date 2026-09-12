package com.gudf.domain;

import java.util.Random;

public class User {
    private String username;
    private String password;
    private String id;
    private boolean state;

    public String CreatId(){
        StringBuilder sb = new StringBuilder("shici");
        Random r = new Random();
        for (int i = 0 ;i <5 ; i++) {
            sb.append(r.nextInt(1,10));
        }
        return sb.toString();
    }

    public User() {
        id = CreatId();
        this.state = true;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        id = CreatId();
        state = true;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
