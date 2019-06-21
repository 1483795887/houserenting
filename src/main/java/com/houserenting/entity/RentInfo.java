package com.houserenting.entity;

public class RentInfo {
    private int rid;
    private int cid;
    private String huxing;
    private float mianji;
    private String time;
    private String zhuangxiu;
    private float price;
    private String address;
    private String pic;



    //{"huxing":"adasf","customer":{"cid":0"}}

    public RentInfo() {
        huxing = "";
        zhuangxiu = "";
        price = 0.0f;
        address = "";
        pic = "";
        time = "";
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getHuxing() {
        return huxing;
    }

    public void setHuxing(String huxing) {
        if (huxing != null)
            this.huxing = huxing;
    }

    public float getMianji() {
        return mianji;
    }

    public void setMianji(float mianji) {
        this.mianji = mianji;
    }

    public String getZhuangxiu() {
        return zhuangxiu;
    }

    public void setZhuangxiu(String zhuangxiu) {
        if (zhuangxiu != null)
            this.zhuangxiu = zhuangxiu;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        if (pic != null)
            this.pic = pic;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        if (time != null)
            this.time = time;
    }

    public int getCid(){
        return cid;
    }

    public void setCid(int cid){
        this.cid = cid;
    }
}
