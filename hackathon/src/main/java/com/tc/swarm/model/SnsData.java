package com.tc.swarm.model;

public class SnsData {
    private String longitude;
    private String latitude;
    private String carModel;
    private String lastCarWashDate;
    private String weatherCondition;

    public SnsData(String longitude, String latitude, String carModel, String lastCarWashDate, String weatherCondition) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.carModel = carModel;
        this.lastCarWashDate = lastCarWashDate;
        this.weatherCondition = weatherCondition;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getLastCarWashDate() {
        return lastCarWashDate;
    }

    public void setLastCarWashDate(String lastCarWashDate) {
        this.lastCarWashDate = lastCarWashDate;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }
}
