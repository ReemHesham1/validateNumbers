package com.numbervalidator.exercise.countryprocessor;


import com.numbervalidator.exercise.exceptions.InvalidCountryCodeException;
import com.numbervalidator.exercise.exceptions.InvalidCountryNameException;
import com.numbervalidator.exercise.datamodel.Country;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CountryFetcher {


    private final Map<String, Country> codeToCountriesMap = new HashMap<>();
    private final Map<String, String> countryNameToCountryCodeMap = new HashMap<>();

    public CountryFetcher() {
        initCountryNameToCountryCodeMap ();
        initCodeToCountriesMap();
    }


    public Country getCountryFromCode(String code) {
        if (!codeToCountriesMap.containsKey(code)) {
            log.error("Invalid code: " + code);
            throw new InvalidCountryCodeException("Invalid country code: " + code);
        }
        return this.codeToCountriesMap.get(code);
    }

    public String getCodeFromCountry(String countryName) {
        String capitalizeCountryName = StringUtils.capitalize(countryName.toLowerCase());
        if (!countryNameToCountryCodeMap.containsKey(capitalizeCountryName)) {
            log.error("Invalid country name: " + countryName);
            throw new InvalidCountryNameException("Invalid country name: " + countryName);
        }
        return this.countryNameToCountryCodeMap.get(capitalizeCountryName);
    }

    public List<String> getCountriesNames() {
        return new ArrayList<>(this.countryNameToCountryCodeMap.keySet());
    }

    private void initCountryNameToCountryCodeMap (){
        countryNameToCountryCodeMap.put("Cameroon", "(237)");
        countryNameToCountryCodeMap.put("Ethiopia", "(251)");
        countryNameToCountryCodeMap.put("Morocco", "(212)");
        countryNameToCountryCodeMap.put("Mozambique", "(258)");
        countryNameToCountryCodeMap.put("Uganda", "(256)");
    }

    private void initCodeToCountriesMap(){
        codeToCountriesMap.put("(237)", new Country("Cameroon", "(237)", "\\(237\\)\\ ?[2368]\\d{7,8}$"));
        codeToCountriesMap.put("(251)", new Country("Ethiopia", "(251)", "\\(251\\)\\ ?[1-59]\\d{8}$"));
        codeToCountriesMap.put("(212)", new Country("Morocco", "(212)", "\\(212\\)\\ ?[5-9]\\d{8}$"));
        codeToCountriesMap.put("(258)", new Country("Mozambique", "(258)", "\\(258\\)\\ ?[28]\\d{7,8}$"));
        codeToCountriesMap.put("(256)", new Country("Uganda", "(256)", "\\(256\\)\\ ?\\d{9}$"));

    }
}
