package com.github.overseasbasketballconnection.locations.controllers;

import com.github.overseasbasketballconnection.locations.services.LocationQuerier;
import com.github.overseasbasketballconnection.locations.models.printfullocation.PrintfulCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author leonhunter
 * @created 03/27/2020 - 5:17 PM
 * perhaps integrate  https://api.printful.com/countries
 */
@Controller
@RequestMapping(value = "/country")
public class CountryController {
    private LocationQuerier locationQuerier;

    @Autowired
    public CountryController(LocationQuerier locationQuerier) {
        this.locationQuerier = locationQuerier;
    }

    @GetMapping("/")
    public ResponseEntity<List<PrintfulCountry>> all() {
        return new ResponseEntity<>(locationQuerier.getAllCountries(), HttpStatus.OK);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> names() {
        return new ResponseEntity<>(locationQuerier.getAllCountryNames(), HttpStatus.OK);
    }

    @GetMapping("/{countryName}")
    public ResponseEntity<PrintfulCountry> getCountry(@PathVariable String countryName) {
        return new ResponseEntity<>(locationQuerier.getCountry(countryName), HttpStatus.OK);
    }

    @GetMapping("/{countryName}/states")
    public ResponseEntity<List<String>> getStatesByCountry(@PathVariable String countryName) {
        return new ResponseEntity<>(locationQuerier.getAllStateNames(countryName), HttpStatus.OK);
    }
}
