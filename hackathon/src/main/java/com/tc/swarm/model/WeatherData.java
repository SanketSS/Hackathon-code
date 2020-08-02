package com.tc.swarm.model;

public class WeatherData {
    private String zipCode;
    private String condition;

    public WeatherData(String zipCode, String condition) {
        this.zipCode = zipCode;
        this.condition = condition;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }


    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
