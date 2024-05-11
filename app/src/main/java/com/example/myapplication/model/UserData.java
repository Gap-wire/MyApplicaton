package com.example.myapplication.model;

public class UserData {
    private String dataName;
    private String hostName;

    public UserData(String dataName, String hostName) {
        this.dataName = dataName;
        this.hostName = hostName;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}