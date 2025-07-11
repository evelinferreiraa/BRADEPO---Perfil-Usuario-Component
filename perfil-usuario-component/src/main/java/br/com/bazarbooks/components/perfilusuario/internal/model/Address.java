package br.com.bazarbooks.components.perfilusuario.internal.model;

import java.util.Objects;

public class Address {
    private int id;
    private String street;
    private int number;
    private String complement;
    private String district;
    private String city;
    private String state;
    private String postalCode;
    private double latitude;
    private double longitude;
    private int userId;

    public Address() {
    }

    public Address(String street, int number, String complement, String district, String city,
            String state, String postalCode, double latitude, double longitude, int userId) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userId = userId;
        this.id = 0;
    }

    public Address(int id, String street, int number, String complement, String district, String city,
            String state, String postalCode, double latitude, double longitude, int userId) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Address [id=" + id + ", street=" + street + ", city=" + city + ", userId=" + userId + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Address address = (Address) o;
        return id == address.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}