package com.github.overseasbasketballconnection.locations.services;

import com.github.overseasbasketballconnection.locations.models.printfullocation.PrintfulCountry;
import com.github.overseasbasketballconnection.locations.models.printfullocation.PrintfulCountryResult;
import com.github.overseasbasketballconnection.locations.models.printfullocation.PrintfulState;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author leonhunter
 * @created 03/27/2020 - 7:22 PM
 */
@Component
public class LocationQuerier {
    private static final String endpoint = "https://api.printful.com/countries";
    private static final Logger logger = Logger.getLogger(LocationQuerier.class.getName());

    public PrintfulCountryResult getAllCountryResult() {
        logger.info(String.format("Attempting to all-country-results from endpoint [ %s ] ", endpoint));
        PrintfulCountryResult result = new RestTemplate().getForObject(endpoint, PrintfulCountryResult.class);
        logger.info(String.format("Successfully retrieved all-country-results from endpoint [ %s ] ", endpoint));
        logger.info(String.format("List of all all-country-results:\n\t %s", result));
        return result;
    }

    public List<PrintfulCountry> getAllCountries() {
        List<PrintfulCountry> result = getAllCountryResult().getResult();
        logger.info(String.format("List of all countries:\n\t %s", result));
        return result;
    }

    public List<String> getAllCountryNames() {
        return getAllCountries()
                .stream()
                .map(PrintfulCountry::getName)
                .collect(Collectors.toList());
    }


    public PrintfulCountry getCountry(final String countryName) {
        logger.info(String.format("Attempting to find state named [ %s ]", countryName));
        PrintfulCountry result = getAllCountries()
                .stream()
                .filter(country -> country.getName().equalsIgnoreCase(countryName.replaceAll("_", " ")))
                .findFirst()
                .get();
        logger.info(String.format("Successfully retrieved country named [ %s ] ", countryName));
        logger.info(String.format("[ %s ] contents:\n\t", result));
        return result;
    }

    public List<PrintfulState> getAllStates(String countryName) {
        logger.info(String.format("Attempting to get all states from [ %s ]", countryName));
        List<PrintfulState> result = getCountry(countryName).getStates();
        logger.info(String.format("List of all states:\n\t %s", result));
        return result;
    }


    public List<String> getAllStateNames(String countryName) {
        List<PrintfulState> allStates = getAllStates(countryName);
        Stream<PrintfulState> allStatesStream = allStates.stream();
        Stream<String> allStateNames = allStatesStream.map(PrintfulState::getName);
        List<String> result = allStateNames.collect(Collectors.toList());;
        return result;

    }
}
