package com.numbervalidator.exercise.mapper;

import com.numbervalidator.exercise.datamodel.Country;
import com.numbervalidator.exercise.datamodel.CustomerEntity;
import com.numbervalidator.exercise.dto.Customer;
import com.numbervalidator.exercise.countryprocessor.CountryFetcher;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Builder
public class Mapper {

    @Autowired
    private CountryFetcher countryFetcher;


    public Customer mapToDto(CustomerEntity customer) {
        String countryCode = customer.getPhone().split(" ")[0];
        Country country = countryFetcher.getCountryFromCode(countryCode);

        return Customer.builder()
                .id(customer.getId())
                .customerName(customer.getName())
                .phoneNumber(customer.getPhone())
                .countryCode(country.getCountryCode())
                .countryName(country.getCountryName())
                .isValid(validatePhoneNumber(country.getPattern(), customer.getPhone()))
                .build();

    }

    private boolean validatePhoneNumber(String reference, String validationTarget) {
        return validationTarget.matches(reference);
    }
}
