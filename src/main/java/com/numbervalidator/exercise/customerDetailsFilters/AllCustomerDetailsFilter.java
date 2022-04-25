package com.numbervalidator.exercise.customerDetailsFilters;

import com.numbervalidator.exercise.countryprocessor.CountryFetcher;
import com.numbervalidator.exercise.datamodel.CustomerEntity;
import com.numbervalidator.exercise.dto.Customer;
import com.numbervalidator.exercise.mapper.Mapper;
import com.numbervalidator.exercise.repository.CustomerRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class AllCustomerDetailsFilter extends CustomerDetailsFilter{

    private final CountryFetcher countryFetcher;
    private final CustomerRepository customerRepository;

    public AllCustomerDetailsFilter(Mapper mapper, CountryFetcher countryFetcher, CustomerRepository customerRepository) {
        super(mapper);
        this.countryFetcher = countryFetcher;
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<Customer> filterCustomers(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<CustomerEntity> customerPages = customerRepository.findAll(paging);
        return mapCustomerEntityToDto(customerPages);
    }
}
