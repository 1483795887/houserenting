package com.houserenting.entity;

public class Message {
    private int mid;
    private String title;
    private String content;
    private String time;
    private int rid;
    private Customer customer;


    public Message() {
        title = "";
        content = "";
        time = "";
        customer = new Customer();
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null)
            this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content != null)
            this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        if (time != null)
            this.time = time;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer != null)
            this.customer = customer;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }
}
