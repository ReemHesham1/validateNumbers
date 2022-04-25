package com.numbervalidator.exercise.customerDetailsFilters;

import com.numbervalidator.exercise.datamodel.CustomerEntity;
import com.numbervalidator.exercise.dto.Customer;
import com.numbervalidator.exercise.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class CustomerDetailsFilter {

    private final Mapper mapper;

    protected CustomerDetailsFilter(Mapper mapper) {
        this.mapper = mapper;
    }

    public abstract Page<Customer> filterCustomers(int page, int size);

    protected List<Customer> createCustomerList(List<CustomerEntity> customers) {
        return customers.stream()
                .map(customer -> mapper.mapToDto(customer))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    protected List<Customer> getFilteredCustomersList(String state, List<Customer> customersDtoTempList) {
        List<Customer> filteredCustomersList;
        Boolean isValidState = getState(state);
        filteredCustomersList = customersDtoTempList.stream()
                .filter(customerDto -> isValidState != null && customerDto.isValid() == isValidState)
                .collect(Collectors.toList());
        return filteredCustomersList;
    }

    protected Page<Customer> getCustomerPage(int pageNumber, int pageSize, List<Customer> filteredList) {
        if (filteredList == null) {
            throw new IllegalArgumentException("the list should not be null");
        }
        int startOfThePage = pageNumber * pageSize;
        List<Customer> numbersDtoList;

        if (filteredList.size() < startOfThePage) {
            numbersDtoList = Collections.emptyList();
        } else {
            int endOfPage = Math.min(startOfThePage + pageSize, filteredList.size());
            numbersDtoList = filteredList.subList(startOfThePage, endOfPage);
        }
        return new PageImpl<>(numbersDtoList, PageRequest.of(pageNumber, pageSize),
                filteredList.size());
    }

    protected Page<Customer> mapCustomerEntityToDto(Page<CustomerEntity> customerPages) {
        return customerPages.map(mapper::mapToDto);
    }

    private Boolean getState(String state) {
        if (state.equalsIgnoreCase("valid number")) {
            return true;
        } else if (state.equalsIgnoreCase("invalid number")) {
            return false;
        }
        return null;
    }
}
