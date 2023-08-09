package com.bestbuy.model;

public class StorePojo {

    private String name;
    private String type;
    private String address;

    private String address2;
    private String city;

    private String state;
    private String zip;
    private int lat;

    private int lng;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = Integer.parseInt(lat);
    }

    public int getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = Integer.parseInt(lng);
    }


    public static StorePojo getStoresPojo(String name, String type, String address, String address2, String city, String state, String zip) {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        return storePojo;
    }

}
