package com.houserenting.entity;

public class Message {
    private int mid;
    private String title;
    private String content;
    private String time;
    private int cid;
    private int rid;

    public Message() {
        title = "";
        content = "";
        time = "";

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

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }
}
