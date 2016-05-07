package com.example.pc.onlinesoccer.MainScreen.Match;

/**
 * Created by PDKPRO on 27/04/2016.
 */
public class Slots {
    private String id,match_id,user_id;
    private int quantity;
    private boolean verified;

    public Slots(String id,String match_id,int quantity) {
        this.match_id = match_id;
        this.id = id;
        this.quantity = quantity;
        this.verified = true;
    }

    public Slots(String match_id, String user_id) {
        this.match_id = match_id;
        this.user_id = user_id;
        this.quantity = 1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
