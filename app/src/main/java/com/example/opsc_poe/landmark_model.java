package com.example.opsc_poe;

public class landmark_model {

    String landmark_name;
    String categoty_name;
    String favorite;


    public landmark_model(String landmark_name, String categoty_name, String favorite) {
        this.landmark_name = landmark_name;
        this.categoty_name = categoty_name;
        this.favorite = favorite;
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
}
