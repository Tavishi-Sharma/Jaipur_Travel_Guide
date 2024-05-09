package com.example.jaipur_travel_guide.Domains;

public class TempleDomain {
    private String tempImg;
    private String tempName;
    private String tempLoc;

    public TempleDomain(String tempImg, String tempName, String tempLoc) {
        this.tempImg = tempImg;
        this.tempName = tempName;
        this.tempLoc = tempLoc;
    }

    public String getTempImg() {
        return tempImg;
    }

    public void setTempImg(String tempImg) {
        this.tempImg = tempImg;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    public String getTempLoc() {
        return tempLoc;
    }

    public void setTempLoc(String tempLoc) {
        this.tempLoc = tempLoc;
    }
}
