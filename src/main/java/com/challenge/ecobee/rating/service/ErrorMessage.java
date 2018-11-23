package com.challenge.ecobee.rating.service;

public class ErrorMessage {
    private static ErrorMessage errorMessage = new ErrorMessage();

    public static ErrorMessage getInstance() {
        return errorMessage;
    }

    public String DataFormatError;
    public String LocationFormatError;
    public String RValueRangeError;
    public String QueryFormatError;
    public String RValueParsingError;

    private ErrorMessage() {
        DataFormatError = "wrong data format, correct format: \"<name>\" \"<location>\" <rvalue>";
        LocationFormatError = "wrong location format, correct format: <country>/<state>/<city>";
        RValueRangeError = "The allowed range of RValue is 0 - 50 inclusive";
        RValueParsingError = "Error parsing rvalue, possibly due to wrong value type";
        QueryFormatError = "wrong query format, correct format: \"<name>\" \"<region>\"";
    }
}
