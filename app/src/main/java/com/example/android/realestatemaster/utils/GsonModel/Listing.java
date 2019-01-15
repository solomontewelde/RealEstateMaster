package com.example.android.realestatemaster.utils.GsonModel;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Listing implements Serializable {

    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("num_floors")
    @Expose
    private String numFloors;
    @SerializedName("image_150_113_url")
    @Expose
    private String image150113Url;
    @SerializedName("listing_status")
    @Expose
    private String listingStatus;
    @SerializedName("num_bedrooms")
    @Expose
    private String numBedrooms;
    @SerializedName("location_is_approximate")
    @Expose
    private Integer locationIsApproximate;
    @SerializedName("image_50_38_url")
    @Expose
    private String image5038Url;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("furnished_state")
    @Expose
    private Object furnishedState;
    @SerializedName("agent_address")
    @Expose
    private String agentAddress;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("property_type")
    @Expose
    private String propertyType;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("floor_area")
    @Expose
    private FloorArea floorArea;
    @SerializedName("thumbnail_url")
    @Expose
    private String thumbnailUrl;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("post_town")
    @Expose
    private String postTown;
    @SerializedName("details_url")
    @Expose
    private String detailsUrl;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("outcode")
    @Expose
    private String outcode;
    @SerializedName("property_report_url")
    @Expose
    private String propertyReportUrl;
    @SerializedName("image_645_430_url")
    @Expose
    private String image645430Url;
    @SerializedName("county")
    @Expose
    private String county;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("listing_id")
    @Expose
    private String listingId;
    @SerializedName("image_caption")
    @Expose
    private String imageCaption;
    @SerializedName("image_80_60_url")
    @Expose
    private String image8060Url;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("agent_name")
    @Expose
    private String agentName;
    @SerializedName("num_recepts")
    @Expose
    private String numRecepts;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("first_published_date")
    @Expose
    private String firstPublishedDate;
    @SerializedName("displayable_address")
    @Expose
    private String displayableAddress;
    @SerializedName("price_modifier")
    @Expose
    private String priceModifier;
    @SerializedName("floor_plan")
    @Expose
    private List<String> floorPlan = null;
    @SerializedName("street_name")
    @Expose
    private String streetName;
    @SerializedName("num_bathrooms")
    @Expose
    private String numBathrooms;
    @SerializedName("agent_logo")
    @Expose
    private String agentLogo;
    @SerializedName("price_change")
    @Expose
    private List<PriceChange> priceChange = null;
    @SerializedName("agent_phone")
    @Expose
    private String agentPhone;
    @SerializedName("image_354_255_url")
    @Expose
    private String image354255Url;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("last_published_date")
    @Expose
    private String lastPublishedDate;

    /**
     * No args constructor for use in serialization
     *
     */
    public Listing() {
    }

    /**
     *
     * @param image354255Url
     * @param numFloors
     * @param image8060Url
     * @param priceModifier
     * @param listingStatus
     * @param imageUrl
     * @param propertyReportUrl
     * @param displayableAddress
     * @param numBedrooms
     * @param image150113Url
     * @param image5038Url
     * @param lastPublishedDate
     * @param priceChange
     * @param listingId
     * @param locationIsApproximate
     * @param outcode
     * @param description
     * @param thumbnailUrl
     * @param image645430Url
     * @param numRecepts
     * @param longitude
     * @param agentPhone
     * @param postTown
     * @param status
     * @param countryCode
     * @param detailsUrl
     * @param streetName
     * @param furnishedState
     * @param floorPlan
     * @param country
     * @param floorArea
     * @param category
     * @param propertyType
     * @param price
     * @param agentName
     * @param shortDescription
     * @param county
     * @param firstPublishedDate
     * @param agentAddress
     * @param numBathrooms
     * @param imageCaption
     * @param latitude
     * @param agentLogo
     */
    public Listing(String countryCode, String numFloors, String image150113Url, String listingStatus, String numBedrooms, Integer locationIsApproximate, String image5038Url, Double latitude, Object furnishedState, String agentAddress, String category, String propertyType, Double longitude, FloorArea floorArea, String thumbnailUrl, String description, String postTown, String detailsUrl, String shortDescription, String outcode, String propertyReportUrl, String image645430Url, String county, String price, String listingId, String imageCaption, String image8060Url, String status, String agentName, String numRecepts, String country, String firstPublishedDate, String displayableAddress, String priceModifier, List<String> floorPlan, String streetName, String numBathrooms, String agentLogo, List<PriceChange> priceChange, String agentPhone, String image354255Url, String imageUrl, String lastPublishedDate) {
        super();
        this.countryCode = countryCode;
        this.numFloors = numFloors;
        this.image150113Url = image150113Url;
        this.listingStatus = listingStatus;
        this.numBedrooms = numBedrooms;
        this.locationIsApproximate = locationIsApproximate;
        this.image5038Url = image5038Url;
        this.latitude = latitude;
        this.furnishedState = furnishedState;
        this.agentAddress = agentAddress;
        this.category = category;
        this.propertyType = propertyType;
        this.longitude = longitude;
        this.floorArea = floorArea;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.postTown = postTown;
        this.detailsUrl = detailsUrl;
        this.shortDescription = shortDescription;
        this.outcode = outcode;
        this.propertyReportUrl = propertyReportUrl;
        this.image645430Url = image645430Url;
        this.county = county;
        this.price = price;
        this.listingId = listingId;
        this.imageCaption = imageCaption;
        this.image8060Url = image8060Url;
        this.status = status;
        this.agentName = agentName;
        this.numRecepts = numRecepts;
        this.country = country;
        this.firstPublishedDate = firstPublishedDate;
        this.displayableAddress = displayableAddress;
        this.priceModifier = priceModifier;
        this.floorPlan = floorPlan;
        this.streetName = streetName;
        this.numBathrooms = numBathrooms;
        this.agentLogo = agentLogo;
        this.priceChange = priceChange;
        this.agentPhone = agentPhone;
        this.image354255Url = image354255Url;
        this.imageUrl = imageUrl;
        this.lastPublishedDate = lastPublishedDate;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getNumFloors() {
        return numFloors;
    }

    public void setNumFloors(String numFloors) {
        this.numFloors = numFloors;
    }

    public String getImage150113Url() {
        return image150113Url;
    }

    public void setImage150113Url(String image150113Url) {
        this.image150113Url = image150113Url;
    }

    public String getListingStatus() {
        return listingStatus;
    }

    public void setListingStatus(String listingStatus) {
        this.listingStatus = listingStatus;
    }

    public String getNumBedrooms() {
        return numBedrooms;
    }

    public void setNumBedrooms(String numBedrooms) {
        this.numBedrooms = numBedrooms;
    }

    public Integer getLocationIsApproximate() {
        return locationIsApproximate;
    }

    public void setLocationIsApproximate(Integer locationIsApproximate) {
        this.locationIsApproximate = locationIsApproximate;
    }

    public String getImage5038Url() {
        return image5038Url;
    }

    public void setImage5038Url(String image5038Url) {
        this.image5038Url = image5038Url;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Object getFurnishedState() {
        return furnishedState;
    }

    public void setFurnishedState(Object furnishedState) {
        this.furnishedState = furnishedState;
    }

    public String getAgentAddress() {
        return agentAddress;
    }

    public void setAgentAddress(String agentAddress) {
        this.agentAddress = agentAddress;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public FloorArea getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(FloorArea floorArea) {
        this.floorArea = floorArea;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostTown() {
        return postTown;
    }

    public void setPostTown(String postTown) {
        this.postTown = postTown;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getOutcode() {
        return outcode;
    }

    public void setOutcode(String outcode) {
        this.outcode = outcode;
    }

    public String getPropertyReportUrl() {
        return propertyReportUrl;
    }

    public void setPropertyReportUrl(String propertyReportUrl) {
        this.propertyReportUrl = propertyReportUrl;
    }

    public String getImage645430Url() {
        return image645430Url;
    }

    public void setImage645430Url(String image645430Url) {
        this.image645430Url = image645430Url;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public String getImageCaption() {
        return imageCaption;
    }

    public void setImageCaption(String imageCaption) {
        this.imageCaption = imageCaption;
    }

    public String getImage8060Url() {
        return image8060Url;
    }

    public void setImage8060Url(String image8060Url) {
        this.image8060Url = image8060Url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getNumRecepts() {
        return numRecepts;
    }

    public void setNumRecepts(String numRecepts) {
        this.numRecepts = numRecepts;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstPublishedDate() {
        return firstPublishedDate;
    }

    public void setFirstPublishedDate(String firstPublishedDate) {
        this.firstPublishedDate = firstPublishedDate;
    }

    public String getDisplayableAddress() {
        return displayableAddress;
    }

    public void setDisplayableAddress(String displayableAddress) {
        this.displayableAddress = displayableAddress;
    }

    public String getPriceModifier() {
        return priceModifier;
    }

    public void setPriceModifier(String priceModifier) {
        this.priceModifier = priceModifier;
    }

    public List<String> getFloorPlan() {
        return floorPlan;
    }

    public void setFloorPlan(List<String> floorPlan) {
        this.floorPlan = floorPlan;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getNumBathrooms() {
        return numBathrooms;
    }

    public void setNumBathrooms(String numBathrooms) {
        this.numBathrooms = numBathrooms;
    }

    public String getAgentLogo() {
        return agentLogo;
    }

    public void setAgentLogo(String agentLogo) {
        this.agentLogo = agentLogo;
    }

    public List<PriceChange> getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(List<PriceChange> priceChange) {
        this.priceChange = priceChange;
    }

    public String getAgentPhone() {
        return agentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    public String getImage354255Url() {
        return image354255Url;
    }

    public void setImage354255Url(String image354255Url) {
        this.image354255Url = image354255Url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLastPublishedDate() {
        return lastPublishedDate;
    }

    public void setLastPublishedDate(String lastPublishedDate) {
        this.lastPublishedDate = lastPublishedDate;
    }

}