package com.houserenting.entity;

public class Admin {
    int aid;
    String username;
    String password;
    String tel;

    public Admin() {
        username = "";
        password = "";
        tel = "";
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null)
            username = "";
        else
            this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null)
            password = "";
        else
            this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        if (tel == null)
            tel = "";
        else
            this.tel = tel;
    }
}
