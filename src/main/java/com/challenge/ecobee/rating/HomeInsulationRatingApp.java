package com.challenge.ecobee.rating;

import com.challenge.ecobee.rating.service.InsulationRater;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class HomeInsulationRatingApp {
    private static Logger logger = Logger.getLogger(HomeInsulationRatingApp.class.toString());

    public static void main(String[] args) {
        logger.info("Starting ThermostatRating App...\nPlease enter data and query input Below:\n");
        Scanner scanner = new Scanner(System.in);
        queryReaderToProcess(scanner, dataReader(scanner));
        scanner.close();
    }

    public static InsulationRater dataReader(Scanner scanner) {
        InsulationRater insulationRater = new InsulationRater();
        String data;
        while(scanner.hasNextLine()) {
            data = scanner.nextLine();
            if (data.isEmpty()) { break; }
            insulationRater.persist(data);
        }
        return insulationRater;
    }

    private static void queryReaderToProcess(Scanner scanner, InsulationRater insulationRater) {
        List<String> result = new ArrayList<>();
        String query;
        while(scanner.hasNextLine()) {
            query = scanner.nextLine();
            if(query.isEmpty()) { break; }
            result.add(insulationRater.processQuery(query));
        }
        System.out.println(String.join("\n", result));
    }
}
