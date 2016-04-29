package com.example.pc.onlinesoccer.MainScreen.Field;

/**
 * Created by PDKPRO on 27/04/2016.
 */
public class Fields {
    private int id;
    private String name;
    private String address;
    private String phone;
    private int countStadium;
    private int priceSpecial;
    private int priceNormal;
    private String flagName;




    public  Fields(int id, String name, String address, String phone, int countStadium,
                   int priceSpecial, int priceNormal,String flagName){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.countStadium = countStadium;
        this.priceNormal = priceNormal;
        this.priceSpecial = priceSpecial;
        this.flagName = flagName;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCountStadium() {
        return countStadium;
    }

    public void setCountStadium(int countStadium) {
        this.countStadium = countStadium;
    }

    public int getPriceSpecial() {
        return priceSpecial;
    }

    public void setPriceSpecial(int priceSpecial) {
        this.priceSpecial = priceSpecial;
    }

    public int getPriceNormal() {
        return priceNormal;
    }

    public void setPriceNormal(int priceNormal) {
        this.priceNormal = priceNormal;
    }

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
