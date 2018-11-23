package com.challenge.ecobee.rating.model;


public class Customer implements Comparable<Customer> {

    private String name;
    private Location location;
    private Float rvalue;

    public Customer(String name, Float rvalue, Location location) {
        this.name = name;
        this.rvalue = rvalue;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRvalue() {
        return rvalue;
    }

    public void setRvalue(Float rvalue) {
        this.rvalue = rvalue;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public int compareTo(Customer customer) {
        int comparisonResult = this.getRvalue().compareTo(customer.rvalue);
        if(comparisonResult == 0) {
            if(this.equals(customer)) return 0;
            else return 1;
        }
        return comparisonResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        if (!name.equals(customer.name)) return false;
        if (!rvalue.equals(customer.rvalue)) return false;
        if (!location.equals(customer.location)) return false;
        return false;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + rvalue.hashCode();
        result = 31 * result + location.hashCode();
        return result;
    }
}