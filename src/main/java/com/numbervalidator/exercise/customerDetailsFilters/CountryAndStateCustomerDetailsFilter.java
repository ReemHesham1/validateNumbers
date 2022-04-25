package com.numbervalidator.exercise.customerDetailsFilters;

import com.numbervalidator.exercise.countryprocessor.CountryFetcher;
import com.numbervalidator.exercise.datamodel.CustomerEntity;
import com.numbervalidator.exercise.dto.Customer;
import com.numbervalidator.exercise.exceptions.NoCustomersFoundException;
import com.numbervalidator.exercise.mapper.Mapper;
import com.numbervalidator.exercise.repository.CustomerRepository;

import org.springframework.data.domain.Page;

import java.util.List;

public class CountryAndStateCustomerDetailsFilter extends CustomerDetailsFilter {


    private final CountryFetcher countryFetcher;
    private final CustomerRepository customerRepository;

    private String countryName;
    private String state;

    public CountryAndStateCustomerDetailsFilter(Mapper mapper, CountryFetcher countryFetcher, CustomerRepository customerRepository, String countryName, String state) {
        super(mapper);
        this.countryFetcher = countryFetcher;
        this.customerRepository = customerRepository;
        this.countryName = countryName;
        this.state = state;
    }

    @Override
    public Page<Customer> filterCustomers(int page, int size) {
        String countryCode = countryFetcher.getCodeFromCountry(countryName);
        List<CustomerEntity> customers = filterCustomersByCountry(countryCode);
        List<Customer> customersTempList = createCustomerList(customers);
        List<Customer> filteredCustomersDtoList = getFilteredCustomersList(state, customersTempList);
        return getCustomerPage(page, size, filteredCustomersDtoList);
    }

    private List<CustomerEntity> filterCustomersByCountry(String countryCode) {
        List<CustomerEntity> filtredCustomer = customerRepository.findByPhoneStartsWith(countryCode);
        if (filtredCustomer.size() < 1 || filtredCustomer == null) {
            throw new NoCustomersFoundException("No customers found");
        }
        return filtredCustomer;
    }
}
