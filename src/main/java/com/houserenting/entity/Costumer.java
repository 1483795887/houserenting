package com.houserenting.entity;

public class Costumer {

    public final static int MALE = 0;
    public final static int FEMALE = 1;

    int cid;
    String username;
    String password;
    String realname;
    int sex;
    int age;
    String address;
    String tel;

    public Costumer() {
        username = "";
        password = "";
        realname = "";
        sex = MALE;
        age = 20;
        address = "";
        tel = "";

    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid){
        this.cid = cid;
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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        if (realname == null)
            realname = "";
        else
            this.realname = realname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        if (sex != MALE || sex != FEMALE)
            this.sex = MALE;
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 200)
            this.age = 20;
        else
            this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null)
            this.address = "";
        else
            this.address = address;
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
