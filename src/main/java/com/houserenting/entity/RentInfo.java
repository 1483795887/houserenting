package com.houserenting.entity;

public class RentInfo {
    private int rid;
    private String layout;
    private String renovation;
    private float price;
    private String address;
    private String liaison;
    private String phone;
    private String pic;
    private String publishDate;
    private Customer customer;

    public RentInfo() {
        layout = "";
        renovation = "";
        price = 0.0f;
        address = "";
        liaison = "";
        phone = "";
        pic = "";
        publishDate = "";
        customer = new Customer();
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        if (layout != null)
            this.layout = layout;
    }

    public String getRenovation() {
        return renovation;
    }

    public void setRenovation(String renovation) {
        if (renovation != null)
            this.renovation = renovation;
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

    public String getLiaison() {
        return liaison;
    }

    public void setLiaison(String liaison) {
        if (liaison != null)
            this.liaison = liaison;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone != null)
            this.phone = phone;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        if (pic != null)
            this.pic = pic;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        if (publishDate != null)
            this.publishDate = publishDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer != null)
            this.customer = customer;
    }
}
