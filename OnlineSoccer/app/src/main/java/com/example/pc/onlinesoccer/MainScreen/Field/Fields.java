package com.example.pc.onlinesoccer.MainScreen.Field;

/**
 * Created by PDKPRO on 27/04/2016.
 */
public class Fields {
    private String id,name,distric_id,address,phone;
    private float latitude,longitude;

    public Fields(String id, String name, String distric_id, String address, String phone, float longitude, float latitude) {
        this.id = id;
        this.name = name;
        this.distric_id = distric_id;
        this.address = address;
        this.phone = phone;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDistric_id() {
        return distric_id;
    }

    public void setDistric_id(String distric_id) {
        this.distric_id = distric_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
