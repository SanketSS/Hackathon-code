package com.tc.swarm.model;

public class TelemetryData {
    private String vin;
    private String latitude;
    private String longitude;
    private String zipCode;

    public TelemetryData(String vin, String latitude, String longitude, String zipCode) {
        this.vin = vin;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zipCode = zipCode;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
