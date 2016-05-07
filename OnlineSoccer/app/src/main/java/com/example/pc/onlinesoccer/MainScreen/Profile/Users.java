package com.example.pc.onlinesoccer.MainScreen.Profile;

/**
 * Created by PDKPRO on 22/04/2016.
 */
public class Users {
    private String name,mail,date,imageStr,phone,id;
    public Users() {
    }

    public Users(String name, String mail, String date, String imageStr, String phone) {
        this.name = name;
        this.mail = mail;
        this.date = date;
        this.imageStr = imageStr;
        this.phone = phone;
    }

    public Users( String id,String name) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageStr() {
        return imageStr;
    }

    public void setImageStr(String imageStr) {
        this.imageStr = imageStr;
    }
}
