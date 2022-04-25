package com.numbervalidator.exercise.controller;

import com.numbervalidator.exercise.dto.Customer;
import com.numbervalidator.exercise.restApi.CustomersRestApi;
import com.numbervalidator.exercise.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("customers")
public class CustomersContoller implements CustomersRestApi {

    @Autowired
    private  CustomerService customerService;

    @Override
    @GetMapping("/details")
    public ResponseEntity<Page<Customer>> getCustomersNumbers(String countryName, String state,int page, int size) {
        Page<Customer> customerPage = customerService.getCustomersNumbers(countryName,state,page, size);

        if(customerPage.getTotalElements() > 0) {
            return new ResponseEntity<>(customerPage, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
