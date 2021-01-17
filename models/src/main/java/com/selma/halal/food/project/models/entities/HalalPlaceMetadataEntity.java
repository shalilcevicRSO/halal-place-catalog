package com.selma.halal.food.project.models.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "halal_place_metadata")
@NamedQueries(value =
        {
                @NamedQuery(name = "HalalPlaceMetadataEntity.getAll",
                        query = "SELECT place FROM HalalPlaceMetadataEntity place")
        })


public class HalalPlaceMetadataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "placeName")
    private String name;

    @Column(name = "streetName")
    private String streetName;

    @Column(name = "streetNumber")
    private Integer streetNumber;

    @Column(name = "zipCode")
    private Integer zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "placeType")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "dateCreated")
    private Instant dateCreated;

    @Column(name = "uri")
    private String uri;


    public Integer getPlaceId() { return id; }

    public void setPlaceId(Integer placeId) { this.id = placeId; }

    public String getPlaceName() { return name; }

    public void setPlaceName(String placeName) { this.name = placeName; }

    public String getPlaceType() { return type; }

    public void setPlaceType(String placeType) { this.type = placeType; }

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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
