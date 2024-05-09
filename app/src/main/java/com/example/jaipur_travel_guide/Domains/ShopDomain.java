package com.example.jaipur_travel_guide.Domains;

public class ShopDomain {
    private String iname;
    private String iprice;
    private String ipic;

    public ShopDomain(String iname, String iprice, String ipic) {
        this.iname = iname;
        this.iprice = iprice;
        this.ipic = ipic;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getIprice() {
        return iprice;
    }

    public void setIprice(String iprice) {
        this.iprice = iprice;
    }

    public String getIpic() {
        return ipic;
    }

    public void setIpic(String ipic) {
        this.ipic = ipic;
    }
}
