package com.example.android.realestatemaster.utils.GsonModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PriceChange implements Serializable {

    @SerializedName("direction")
    @Expose
    private String direction;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("percent")
    @Expose
    private String percent;
    @SerializedName("price")
    @Expose
    private String price;

    /**
     * No args constructor for use in serialization
     *
     */
    public PriceChange() {
    }

    /**
     *
     * @param price
     * @param percent
     * @param direction
     * @param date
     */
    public PriceChange(String direction, String date, String percent, String price) {
        super();
        this.direction = direction;
        this.date = date;
        this.percent = percent;
        this.price = price;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}