package com.example.android.realestatemaster.utils.GsonModel;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProportyJsonModel implements Serializable {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("result_count")
    @Expose
    private Integer resultCount;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("area_name")
    @Expose
    private String areaName;
    @SerializedName("listing")
    @Expose
    private List<Listing> listing = null;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("town")
    @Expose
    private String town;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("county")
    @Expose
    private String county;
    @SerializedName("bounding_box")
    @Expose
    private BoundingBox boundingBox;
    @SerializedName("postcode")
    @Expose
    private String postcode;

    /**
     * No args constructor for use in serialization
     *
     */
    public ProportyJsonModel() {
    }

    /**
     *
     * @param boundingBox
     * @param areaName
     * @param county
     * @param resultCount
     * @param street
     * @param listing
     * @param longitude
     * @param latitude
     * @param town
     * @param postcode
     * @param country
     */
    public ProportyJsonModel(String country, Integer resultCount, Double longitude, String areaName, List<Listing> listing, String street, String town, Double latitude, String county, BoundingBox boundingBox, String postcode) {
        super();
        this.country = country;
        this.resultCount = resultCount;
        this.longitude = longitude;
        this.areaName = areaName;
        this.listing = listing;
        this.street = street;
        this.town = town;
        this.latitude = latitude;
        this.county = county;
        this.boundingBox = boundingBox;
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<Listing> getListing() {
        return listing;
    }

    public void setListing(List<Listing> listing) {
        this.listing = listing;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

}