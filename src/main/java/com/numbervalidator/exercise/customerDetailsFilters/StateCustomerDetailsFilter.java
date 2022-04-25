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

import java.util.List;

public class StateCustomerDetailsFilter extends CustomerDetailsFilter{

    private final CountryFetcher countryFetcher;
    private final CustomerRepository customerRepository;

    private String state;

    public StateCustomerDetailsFilter(Mapper mapper, CountryFetcher countryFetcher, CustomerRepository customerRepository, String state) {
        super(mapper);
        this.countryFetcher = countryFetcher;
        this.customerRepository = customerRepository;
        this.state = state;
    }

    @Override
    public Page<Customer> filterCustomers(int page, int size) {
        List<CustomerEntity> customers = customerRepository.findAll();

        List<Customer> customersTempList = createCustomerList(customers);

        List<Customer> filteredCustomersList = getFilteredCustomersList(state, customersTempList);

        if (filteredCustomersList.isEmpty()) {
            throw new NoCustomersFoundException("No customers found");
        }
        return getCustomerPage(page, size, filteredCustomersList);
    }
}
