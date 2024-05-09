package com.example.jaipur_travel_guide.Domains;

public class EventDomain {
    private String title;
    private String picPath;
    private String price;
    private String date;

    public EventDomain(String title, String picPath, String price,String date) {
        this.title = title;
        this.picPath = picPath;
        this.price = price;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
