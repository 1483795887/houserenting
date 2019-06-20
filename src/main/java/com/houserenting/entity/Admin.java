package com.houserenting.entity;

public class Admin {
    private int aid;
    private String username;
    private String password;
    private String tel;

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
            this.username = "";
        else
            this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null)
            this.password = "";
        else
            this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        if (tel == null)
            this.tel = "";
        else
            this.tel = tel;
    }
}
