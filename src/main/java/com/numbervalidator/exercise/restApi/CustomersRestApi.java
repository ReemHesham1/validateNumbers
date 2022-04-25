package com.numbervalidator.exercise.restApi;


import com.numbervalidator.exercise.dto.Customer;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface CustomersRestApi {

    @Operation(summary = "get customers by countryName (country to filter results on) " +
            "or phone number state (phone number state to filter results on) if found")
    ResponseEntity<Page<Customer>> getCustomersNumbers(
            @RequestParam(defaultValue = "All") String countryName,
            @RequestParam(defaultValue = "All") String state,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size);
}
