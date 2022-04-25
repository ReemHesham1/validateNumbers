package com.numbervalidator.exercise.controller;

import com.numbervalidator.exercise.countryprocessor.CountryFetcher;
import com.numbervalidator.exercise.restApi.ContriesRestApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("countries")
@Slf4j
public class ContriesController implements ContriesRestApi {

    @Autowired
    private CountryFetcher countryFetcher;


    @Override
    @GetMapping("/names")
    public ResponseEntity<List<String>> getCountriesList() {
        List<String> countries = countryFetcher.getCountriesNames();

        //Return ok and the list of countries if there are countries in the cache
        if (countries.size() > 0) {
            return new ResponseEntity<>(countries, HttpStatus.OK);
        }

        log.error("Error retrieving countries' list from saved memory!");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
