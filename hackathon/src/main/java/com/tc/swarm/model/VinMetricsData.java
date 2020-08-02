package com.tc.swarm.model;

import java.sql.Timestamp;

public class VinMetricsData {
    private String vin;
    private String gpsDateTime;
    private String latitude;
    private String longitude;
    private String odo;
    private String zipCode;
    private String lastModified;
    private String carModel;
    private String washID;

    public VinMetricsData(String vin, String gpsDateTime, String latitude, String longitude, String odo, String zipCode, String lastModified, String carModel, String washID) {
        this.vin = vin;
        this.gpsDateTime = gpsDateTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.odo = odo;
        this.zipCode = zipCode;
        this.lastModified = lastModified;
        this.carModel = carModel;
        this.washID = washID;
    }

    public VinMetricsData() {
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getGpsDateTime() {
        return gpsDateTime;
    }

    public void setGpsDateTime(String gpsDateTime) {
        this.gpsDateTime = gpsDateTime;
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

    public String getOdo() {
        return odo;
    }

    public void setOdo(String odo) {
        this.odo = odo;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getWashID() {
        return washID;
    }

    public void setWashID(String washID) {
        this.washID = washID;
    }
}
