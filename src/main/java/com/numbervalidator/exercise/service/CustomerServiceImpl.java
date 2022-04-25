package com.numbervalidator.exercise.service;


import com.numbervalidator.exercise.countryprocessor.CountryFetcher;
import com.numbervalidator.exercise.customerDetailsFilters.*;
import com.numbervalidator.exercise.dto.Customer;
import com.numbervalidator.exercise.mapper.Mapper;
import com.numbervalidator.exercise.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {

    public static final String ALL = "All";

    private final CustomerRepository customerRepository;

    private final CountryFetcher countryFetcher;

    private final Mapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CountryFetcher countryFetcher, Mapper mapper) {
        this.customerRepository = customerRepository;
        this.countryFetcher = countryFetcher;
        this.mapper = mapper;
    }

    @Override
    public Page<Customer> getCustomersNumbers(String countryName, String state, int page, int size) {

        return resolveCustomerDetailsFilter(countryName,state).filterCustomers(page, size);

    }

    private CustomerDetailsFilter resolveCustomerDetailsFilter(String countryName,String state) {
        boolean isNotAllCountry = !countryName.equalsIgnoreCase(ALL);
        boolean isNotAllState = !state.equalsIgnoreCase(ALL);
        if (isNotAllCountry && isNotAllState) {
            return new CountryAndStateCustomerDetailsFilter
                    (this.mapper, this.countryFetcher, this.customerRepository, countryName, state);
        } else if (isNotAllCountry) {
            return new CountryCustomerDetailsFilter
                    (this.mapper, this.countryFetcher, this.customerRepository, countryName);
        } else if (isNotAllState) {
            return new StateCustomerDetailsFilter
                    (this.mapper, this.countryFetcher, this.customerRepository, state);
        } else {
            return new AllCustomerDetailsFilter
                    (this.mapper, this.countryFetcher, this.customerRepository);
        }
    }
}
