package com.example.android.realestatemaster.utils.GsonModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FloorArea implements Serializable {

    @SerializedName("max_floor_area")
    @Expose
    private MaxFloorArea maxFloorArea;
    @SerializedName("min_floor_area")
    @Expose
    private MinFloorArea minFloorArea;

    /**
     * No args constructor for use in serialization
     *
     */
    public FloorArea() {
    }

    /**
     *
     * @param minFloorArea
     * @param maxFloorArea
     */
    public FloorArea(MaxFloorArea maxFloorArea, MinFloorArea minFloorArea) {
        super();
        this.maxFloorArea = maxFloorArea;
        this.minFloorArea = minFloorArea;
    }

    public MaxFloorArea getMaxFloorArea() {
        return maxFloorArea;
    }

    public void setMaxFloorArea(MaxFloorArea maxFloorArea) {
        this.maxFloorArea = maxFloorArea;
    }

    public MinFloorArea getMinFloorArea() {
        return minFloorArea;
    }

    public void setMinFloorArea(MinFloorArea minFloorArea) {
        this.minFloorArea = minFloorArea;
    }

}