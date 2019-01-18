package com.example.android.realestatemaster.utils;

/**
 * Created by 100043392 on 17-Apr-18.
 */

public class SearchQuerryBuilder {
    private String area,minPrice,maxPrice,minBeds,maxBeds,propertyType,includeNewHomes,keywords,listing_id,radius,orderBy,listingStatus,pageSize,pageNumber,includeChainFreeHomes, includeSoldHomes;
    private String country,county,street,postCode;
    private String sharedAccomodation,letAgreed;
    final String API_LOCATION = "http://api.zoopla.co.uk/api/v1/property_listings.json?";
    final String API_KEY = "w88jr4f54ba5e4pd4xmfy8ud";
 

    public SearchQuerryBuilder() {
        this.area = "oxford";
        this.minPrice = "";
        this.maxPrice = "";
        this.minBeds = "";
        this.maxBeds = "";
        this.propertyType = "";
        this.includeNewHomes = "";
        this.keywords = "";
        this.listing_id = "";
        this.radius = "";
        this.orderBy = "price";
        this.listingStatus = "";
        this.pageSize = "100";
        this.pageNumber = "";
        this.country = "";
        this.county = "";
        this.street = "";
        this.postCode = "";
        this.includeChainFreeHomes = "";
        this.sharedAccomodation = "";
        this.letAgreed = "";

    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getMinBeds() {
        return minBeds;
    }

    public void setMinBeds(String minBeds) {
        this.minBeds = minBeds;
    }

    public String getMaxBeds() {
        return maxBeds;
    }

    public void setMaxBeds(String maxBeds) {
        this.maxBeds = maxBeds;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getIncludeNewHomes() {
        return includeNewHomes;
    }

    public void setIncludeNewHomes(String includeNewHomes) {
        this.includeNewHomes = includeNewHomes;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getListing_id() {
        return listing_id;
    }

    public void setListing_id(String listing_id) {
        this.listing_id = listing_id;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getListingStatus() {
        return listingStatus;
    }

    public void setListingStatus(String listingStatus) {
        this.listingStatus = listingStatus;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }


    public String getIncludeChainFreeHomes() {
        return includeChainFreeHomes;
    }

    public void setIncludeChainFreeHomes(String includeChainFreeHomes) {
        this.includeChainFreeHomes = includeChainFreeHomes;
    }

    public String getIncludeSoldHomes() {
        return includeSoldHomes;
    }

    public void setIncludeSoldHomes(String includeSoldHomes) {
        this.includeSoldHomes = includeSoldHomes;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    public void setSharedAccomodation(String sharedAccomodation) {
        this.sharedAccomodation = sharedAccomodation;
    }
    public String getSharedAccomodation() {
        return sharedAccomodation;
    }
    public void setLetAgreed(String letAgreed) {
        this.letAgreed = letAgreed;
    }
    public String getLetAgreed() {
        return letAgreed;
    }
    
    public String getQuerryString(){

        String queryString = API_LOCATION +"&api_key="+API_KEY+"&country="+this.getCountry()+"&county="+this.getCounty()+"&street="+this.getStreet()+"&postcode="+this.getPostCode()+
                "&area=" + this.getArea() + "&maximum_price=" + this.getMaxPrice() + "&minimum_price=" + this.getMinPrice() +
                "&minimum_beds=" + this.getMinBeds() + "&maximum_beds=" + this.getMaxBeds() + "&property_type=" + this.getPropertyType()+ "&keywords=" + this.getKeywords() + "&radius="+
                this.getRadius() + "&order_by=" + this.getOrderBy() + "&listing_status=" + this.getListingStatus()+
                "&page_size="+this.getPageSize()+"&page_number="+this.getPageNumber();
                if(this.listingStatus.equalsIgnoreCase("sale")){
                    queryString+= "&new_homes=" + this.getIncludeNewHomes()+"&chain_free="+this.getIncludeChainFreeHomes()+"&include_sold="+this.getIncludeSoldHomes();
                }
                else if(this.listingStatus.equalsIgnoreCase("rent")){
                    queryString+= "&shared_accomodation="+this.getSharedAccomodation()+"&let_agreed="+this.getLetAgreed();
                }
              
        return queryString;
    }
    public String queryByListingId(String listing_id){
        String queryString = API_LOCATION +"&api_key="+API_KEY+"&listing_id="+listing_id;
        return queryString;
    }

 

 



}
