package com.example.android.realestatemaster.utils.GsonModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BoundingBox implements Serializable {

    @SerializedName("longitude_min")
    @Expose
    private String longitudeMin;
    @SerializedName("latitude_min")
    @Expose
    private String latitudeMin;
    @SerializedName("longitude_max")
    @Expose
    private String longitudeMax;
    @SerializedName("latitude_max")
    @Expose
    private String latitudeMax;

    /**
     * No args constructor for use in serialization
     *
     */
    public BoundingBox() {
    }

    /**
     *
     * @param latitudeMin
     * @param latitudeMax
     * @param longitudeMax
     * @param longitudeMin
     */
    public BoundingBox(String longitudeMin, String latitudeMin, String longitudeMax, String latitudeMax) {
        super();
        this.longitudeMin = longitudeMin;
        this.latitudeMin = latitudeMin;
        this.longitudeMax = longitudeMax;
        this.latitudeMax = latitudeMax;
    }

    public String getLongitudeMin() {
        return longitudeMin;
    }

    public void setLongitudeMin(String longitudeMin) {
        this.longitudeMin = longitudeMin;
    }

    public String getLatitudeMin() {
        return latitudeMin;
    }

    public void setLatitudeMin(String latitudeMin) {
        this.latitudeMin = latitudeMin;
    }

    public String getLongitudeMax() {
        return longitudeMax;
    }

    public void setLongitudeMax(String longitudeMax) {
        this.longitudeMax = longitudeMax;
    }

    public String getLatitudeMax() {
        return latitudeMax;
    }

    public void setLatitudeMax(String latitudeMax) {
        this.latitudeMax = latitudeMax;
    }

}