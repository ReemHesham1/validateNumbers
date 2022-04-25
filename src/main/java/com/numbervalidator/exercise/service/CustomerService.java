package com.numbervalidator.exercise.service;

import com.numbervalidator.exercise.dto.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {
    Page<Customer> getCustomersNumbers(String countryName, String state, int page, int size);
}
