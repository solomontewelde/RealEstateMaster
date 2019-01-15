package com.example.android.realestatemaster.HelperClasses;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by 100043392 on 21-Jun-18.
 */

public class PropertyDataBinder extends BaseObservable {
    private String price,bedRooms, bathRooms, area , address, phoneNumber, details;

    public PropertyDataBinder(String price, String bedRooms, String bathRooms, String area, String address, String phoneNumber, String details) {
        this.price = price;
        this.bedRooms = bedRooms;
        this.bathRooms = bathRooms;
        this.area = area;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.details = details;
    }

    public PropertyDataBinder() {
        this.bedRooms = "0" ;
        this.bathRooms = "0";
        this.area = "0";
        this.address = "" ;
        this.phoneNumber = "0" ;
        this.details = "";
        this.price = "0";
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    @Bindable
    public String getBedRooms() {
        return bedRooms;
    }
    @Bindable
    public String getBathRooms() {
        return bathRooms;
    }
    @Bindable
    public String getArea() {
        return area;
    }
    @Bindable
    public String getAddress() {
        return address;
    }
    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }
    @Bindable
    public String getDetails() {
        return details;
    }

    public void setBedRooms(String bedRooms) {
        this.bedRooms = bedRooms;
    }

    public void setBathRooms(String bathRooms) {
        this.bathRooms = bathRooms;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
