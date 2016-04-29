package com.example.pc.onlinesoccer.MainScreen.Match;

import java.io.Serializable;

/**
 * Created by PDKPRO on 27/04/2016.
 */
public class Matchs implements Serializable{
    private String id, field_id,host_id,startTime,endTime;
    private int maxPlayer,status;
    private boolean verified;

    public Matchs(String id, String field_id, String host_id, int maxPlayer, int status, String startTime, String endTime, boolean verified) {
        this.id = id;
        this.field_id = field_id;
        this.host_id = host_id;
        this.maxPlayer = maxPlayer;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.verified = verified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getField_id() {
        return field_id;
    }

    public void setField_id(String field_id) {
        this.field_id = field_id;
    }

    public String getHost_id() {
        return host_id;
    }

    public void setHost_id(String host_id) {
        this.host_id = host_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public void setMaxPlayer(int maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
