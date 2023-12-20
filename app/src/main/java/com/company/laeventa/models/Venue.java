package com.company.laeventa.models;

public class Venue {

    private String name;
    private String price;
    private String known_for;
    private String capacity;
    private String image;
    private Boolean isSports;
    private float stars;


    public Venue(String name, String price, String known_for, String capacity, String image,Boolean isSports, float stars) {
        this.name = name;
        this.price = price;
        this.known_for = known_for;
        this.capacity = capacity;
        this.image = image;
        this.stars = stars;
        this.isSports = isSports;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getKnown_for() {
        return known_for;
    }

    public void setKnown_for(String known_for) {
        this.known_for = known_for;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Boolean getSports() {
        return isSports;
    }

    public void setSports(Boolean sports) {
        isSports = sports;
    }
}
