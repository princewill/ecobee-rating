package com.challenge.ecobee.rating.model;

public class Location implements Comparable<Location> {
    private String city;
    private String province;
    private String country;

    public Location(String country, String province, String city) {
        this.country = country;
        this.province = province;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public int hashCode() {
        int result = country.hashCode();
        result = 31 * result + province.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Location location = (Location) obj;
        return country.equals(location.country) && province.equals(location.province) && city.equals(location.city);
    }

    @Override
    public int compareTo(Location location) {
        int comparisonResult = this.city.compareTo(location.city);
        if(comparisonResult == 0) {
            if(this.equals(location)) return 0;
            else return 1;
        }
        return comparisonResult;
    }

}