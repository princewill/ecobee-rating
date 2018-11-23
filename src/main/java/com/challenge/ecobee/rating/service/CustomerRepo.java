package com.challenge.ecobee.rating.service;

import com.challenge.ecobee.rating.model.Customer;
import com.challenge.ecobee.rating.model.Location;
import com.challenge.ecobee.rating.service.ErrorMessage;

import java.util.Set;
import java.util.TreeSet;

public class CustomerRepo {
    protected TreeSet<Customer> customers = new TreeSet<>();
    protected ErrorMessage errorMessage = ErrorMessage.getInstance();
    public static final String REGEX = " (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

    public void persist(String dataEntry) { customers.add(parseCustomer(dataEntry)); }

    private Customer parseCustomer(String dataEntry) {

        String[] data = dataEntry.split(REGEX);

        if(data.length != 3) { throw new IllegalArgumentException(errorMessage.DataFormatError); }

        String name = data[0].replaceAll("\"", "");
        String[] location = data[1].replaceAll("\"", "").split("/");
        float rValue;

        try { rValue = Float.valueOf(data[2]); }
        catch (Exception e) { throw new IllegalArgumentException(errorMessage.RValueParsingError + e.getMessage()); }

        if(rValue > 50.0 || rValue < 0.0) { throw new IllegalArgumentException(errorMessage.RValueRangeError); }

        if(location.length != 3) { throw new IllegalArgumentException(errorMessage.LocationFormatError); }

        String country = location[0];
        String province = location[1];
        String city = location[2];

        return new Customer(name, rValue, new Location(country, province, city));
    }
    public Set<Customer> getCustomerRepo() { return customers; }
}