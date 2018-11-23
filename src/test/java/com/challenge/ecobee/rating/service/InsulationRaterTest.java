package com.challenge.ecobee.rating.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class InsulationRaterTest {

    protected ErrorMessage errorMessage = ErrorMessage.getInstance();

    @Test
    @DisplayName("Invalid single entry data [No name] results in IllegalArgumentException")
    public void testNoName() {
        InsulationRater insulationRater = new InsulationRater();
        Executable closureContainingCodeToTest = () -> insulationRater.persist("\"Canada/Ontario/Toronto\" 1.5");;
        assertThrows(IllegalArgumentException.class, closureContainingCodeToTest, errorMessage.DataFormatError);
    }

    @Test
    @DisplayName("Invalid single entry Query [No location] results in IllegalArgumentException")
    public void testNoLocation() {
        InsulationRater insulationRater = new InsulationRater();
        Executable closureContainingCodeToTest = () -> insulationRater.persist("\"John Doe\" \"CanadaToronto\" 1.5");
        assertThrows(IllegalArgumentException.class, closureContainingCodeToTest, errorMessage.LocationFormatError);
    }

    @Test
    @DisplayName("Invalid rValue [rvalue > 50.0] results in IllegalArgumentException")
    public void testWrongRValue() {
        InsulationRater insulationRater = new InsulationRater();
        Executable closureContainingCodeToTest = () -> insulationRater.persist("\"John Doe\" \"Canada/Ontario/City\" 60.0");
        assertThrows(IllegalArgumentException.class, closureContainingCodeToTest, errorMessage.RValueRangeError);
    }

    @Test
    @DisplayName("Invalid rValue [rvalue wrong data-type] results in IllegalArgumentException")
    public void testRValueNotValid() {
        InsulationRater insulationRater = new InsulationRater();
        Executable closureContainingCodeToTest = () -> insulationRater.persist("\"John Doe\" \"Canada/Ontario/City\" What");
        assertThrows(IllegalArgumentException.class, closureContainingCodeToTest, errorMessage.RValueParsingError);
    }

    @Test
    @DisplayName("Single data entry is successfully persisted")
    public void testDataForSingleEntry() {
        InsulationRater insulationRater = new InsulationRater();
        insulationRater.persist("\"John Doe\" \"Canada/Ontario/Toronto\" 1.5");
        assertEquals(1,insulationRater.getCustomerRepo().size());
    }

    @Test
    @DisplayName("Multiple data entries are successfully persisted")
    public void testDataForMultipleEntry() {
        InsulationRater insulationRater = new InsulationRater();
        insulationRater.persist("\"John Doe\" \"Canada/Ontario/Toronto\" 1.5");
        insulationRater.persist("\"Samanta Smith\" \"Canada/Ontario/London\" 3.7");
        assertEquals(2,insulationRater.getCustomerRepo().size());
    }

    @Test
    @DisplayName("Single Query results correct rating")
    public void testSingleQueryResultsCorrectRating() {
        InsulationRater insulationRater = new InsulationRater();
        insulationRater.persist("\"John Doe\" \"Canada/Ontario/Toronto\" 1.5");
        String queryResult = insulationRater.processQuery("\"John Doe\" \"Canada\"");
        assertEquals("\"John Doe\" \"Canada\" 10", queryResult);
    }

    @Test
    @DisplayName("Multiple Query results correct ratings")
    public void testMultipleQueryResultsCorrectRating() {
        InsulationRater insulationRater = new InsulationRater();
        insulationRater.persist("\"John Doe\" \"Canada/Ontario/Toronto\" 1.5");
        insulationRater.persist("\"Samanta Smith\" \"Canada/Ontario/London\" 3.7");
        insulationRater.persist("\"Adam Xin\" \"Canada/British Columbia/Vancouver\" 2.110");
        insulationRater.persist("\"Monica Taylor\" \"Canada/Ontario/Toronto\" 2.110");
        insulationRater.persist("\"Alicia Yazzie\" \"US/Arizona/Phoenix\" 5.532");
        insulationRater.persist("\"Mohammed Zadeh\" \"Canada/Ontario/Toronto\" 1.43");

        assertEquals("\"John Doe\" \"Canada\" 4", insulationRater.processQuery("\"John Doe\" \"Canada\""));
        assertEquals("\"John Doe\" \"Canada/Ontario\" 5", insulationRater.processQuery("\"John Doe\" \"Canada/Ontario\""));
        assertEquals("\"Alicia Yazzie\" \"US/Arizona\" 10", insulationRater.processQuery("\"Alicia Yazzie\" \"US/Arizona\""));
    }

}