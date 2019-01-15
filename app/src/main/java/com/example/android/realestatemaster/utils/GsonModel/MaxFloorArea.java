package com.example.android.realestatemaster.utils.GsonModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MaxFloorArea implements Serializable {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("units")
    @Expose
    private String units;

    /**
     * No args constructor for use in serialization
     *
     */
    public MaxFloorArea() {
    }

    /**
     *
     * @param value
     * @param units
     */
    public MaxFloorArea(String value, String units) {
        super();
        this.value = value;
        this.units = units;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

}