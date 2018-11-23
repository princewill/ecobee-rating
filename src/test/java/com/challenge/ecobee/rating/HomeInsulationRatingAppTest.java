package com.challenge.ecobee.rating;

import static org.junit.jupiter.api.Assertions.*;

import com.challenge.ecobee.rating.HomeInsulationRatingApp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HomeInsulationRatingAppTest {
    private static final String CORRECT_QUERY_DATA = "/correct-data-correct-query.txt";
    private static final String SUCCESSFUL_RESULT = "/successful-result.txt";

    @Test
    @DisplayName("Print out correct result when data and query are correct")
    public void testCorrectDataTest() throws IOException, URISyntaxException {
        InputStream sampleInput = this.getClass().getResourceAsStream(CORRECT_QUERY_DATA);
        System.setIn(sampleInput);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        HomeInsulationRatingApp application = new HomeInsulationRatingApp();
        application.main(null);

        String expectedOutput = new String(Files.readAllBytes(Paths.get(this.getClass().getResource(SUCCESSFUL_RESULT).toURI())));
        assertEquals(expectedOutput.toString(), outputStream.toString());

    }
}