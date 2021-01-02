package com.selma.halal.food.project.lib;

import java.time.Instant;

public class HalalPlaceMetadata {

    private Integer placeId;
    private String placeName;
    private String streetName;
    private Integer streetNumber;
    private Integer zipCode;
    private String city;
    private String country;
    private String placeType;
    private String description;
    private Instant dateCreated;
    private String uri;

    public Integer getPlaceId() { return placeId; }

    public void setPlaceId(Integer placeId) { this.placeId = placeId; }

    public String getPlaceName() { return placeName; }

    public void setPlaceName(String placeName) { this.placeName = placeName; }

    public String getPlaceType() { return placeType; }

    public void setPlaceType(String placeType) { this.placeType = placeType; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Instant getDateCreated() {return dateCreated; }

    public void setDateCreated(Instant dateCreated) {this.dateCreated = dateCreated; }


    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUri() {return uri;}

    public void setUri(String uri) {this.uri = uri;}
}
