package com.challenge.ecobee.rating.service;

import com.challenge.ecobee.rating.model.Customer;

import java.util.*;

public class InsulationRater extends CustomerRepo {

    public String processQuery(String queryEntry) {
        String[] query = queryEntry.split(REGEX);

        if (query.length != 2) { throw new IllegalArgumentException(errorMessage.QueryFormatError); }

        String[] region = query[1].replaceAll("\"", "").split("/");
        String customerQueryName = query[0].replaceAll("\"", "");

        float customerQueryRValue = 0;
        int homesInRegionCount = 0;
        int homesWithBetterScoreCount = 0;
        List<Float> sameRegionRValues = new ArrayList<>();

        for ( Customer customer : customers)
        {
            if(isCustomerInQueryRegion(customer, region)) {
                homesInRegionCount++;
                sameRegionRValues.add(customer.getRvalue());
                if(customer.getName().equals(customerQueryName)) {
                    customerQueryRValue = customer.getRvalue();
                }
            }
        }
        for (Float value : sameRegionRValues)
        { if (value > customerQueryRValue) { homesWithBetterScoreCount++; } }
        int rating = evaluteRating(homesWithBetterScoreCount, homesInRegionCount);
        return ("\"" + customerQueryName + "\" " + query[1] + " " + rating);
    }

    private boolean isCustomerInQueryRegion(Customer customer, String[] region) {
        boolean bool = false;
        switch (region.length) {
            case 3:
                bool =
                        customer.getLocation().getCountry().equals(region[0]) &&
                        customer.getLocation().getProvince().equals(region[1]) &&
                        customer.getLocation().getCity().equals(region[2]);
                break;
            case 2:
                bool =
                        customer.getLocation().getCountry().equals(region[0]) &&
                        customer.getLocation().getProvince().equals(region[1]);
                break;
            case 1:
                bool = customer.getLocation().getCountry().equals(region[0]);
                break;
            default:
                break;
        }
        return bool;
    }

    private int evaluteRating(int homesWithBetterScoreCount, int homesInRegionCount) {
        float percentage = ((float) homesWithBetterScoreCount/homesInRegionCount) * 100;

        if(percentage >=90) return 1;
        else if(percentage >=80) return 2;
        else if(percentage >=70) return 3;
        else if(percentage >= 60) return 4;
        else if(percentage >= 50) return 5;
        else if(percentage >=40) return 6;
        else if(percentage >=30) return 7;
        else if(percentage >=20) return 8;
        else if(percentage >=10) return 9;
        else return 10;
    }

}
