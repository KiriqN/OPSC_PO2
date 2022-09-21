package com.example.opsc_poe;

public class landmark_model {

    String landmark_name;
    String categoty_name;
    String favorite;
    int lat;
    int log;


    public landmark_model(String landmark_name, String categoty_name, String favorite, int lat, int log) {
        this.landmark_name = landmark_name;
        this.categoty_name = categoty_name;
        this.favorite = favorite;
        this.lat = lat;
        this.log = log;
    }

    public String getLandmark_name() {
        return landmark_name;
    }

    public String getCategoty_name() {
        return categoty_name;
    }

    public String getFavorite() {
        return favorite;
    }

    public int getLat() {
        return lat;
    }

    public int getLog() {
        return log;
    }

    public void setLandmark_name(String landmark_name) {
        this.landmark_name = landmark_name;
    }

    public void setCategoty_name(String categoty_name) {
        this.categoty_name = categoty_name;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public void setLog(int log) {
        this.log = log;
    }
}
