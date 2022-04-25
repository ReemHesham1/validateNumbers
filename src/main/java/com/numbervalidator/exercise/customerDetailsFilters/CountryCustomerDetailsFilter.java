package com.numbervalidator.exercise.customerDetailsFilters;

import com.numbervalidator.exercise.countryprocessor.CountryFetcher;
import com.numbervalidator.exercise.datamodel.CustomerEntity;
import com.numbervalidator.exercise.dto.Customer;
import com.numbervalidator.exercise.exceptions.NoCustomersFoundException;
import com.numbervalidator.exercise.mapper.Mapper;
import com.numbervalidator.exercise.repository.CustomerRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


public class CountryCustomerDetailsFilter extends CustomerDetailsFilter{

    private final CountryFetcher countryFetcher;
    private final CustomerRepository customerRepository;

    private String countryName;

    public CountryCustomerDetailsFilter(Mapper mapper, CountryFetcher countryFetcher, CustomerRepository customerRepository, String countryName) {
        super(mapper);
        this.countryFetcher = countryFetcher;
        this.customerRepository = customerRepository;
        this.countryName = countryName;
    }

    @Override
    public Page<Customer> filterCustomers(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        String countryCode = countryFetcher.getCodeFromCountry(countryName);
        Page<CustomerEntity> customerPagesByCountry = customerRepository.findByPhoneStartsWith(countryCode, paging);
        if (customerPagesByCountry == null || customerPagesByCountry.getTotalElements() == 0) {
            throw new NoCustomersFoundException("No customers found for the chosen contry ");
        }
        return mapCustomerEntityToDto(customerPagesByCountry);
    }
}
