package com.example.opsc_poe;

public class SettingsType {

    String measurement;

    String preferred_landmark;


    public SettingsType(String measurement, String preferred_landmark) {
        this.measurement = measurement;
        this.preferred_landmark = preferred_landmark;
    }

    public String getMeasurement() {
        return measurement;
    }

    public String getPreferred_landmark() {
        return preferred_landmark;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public void setPreferred_landmark(String preferred_landmark) {
        this.preferred_landmark = preferred_landmark;
    }
}
