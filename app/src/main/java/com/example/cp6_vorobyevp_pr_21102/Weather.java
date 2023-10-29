package com.example.cp6_vorobyevp_pr_21102;

public class Weather {
    private String date;
    private String temp;
    private String url;

    public Weather(String date, String temp, String url) {
        this.date = date;
        this.temp = temp;
        this.url = url;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTemp() {
        return this.temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}

