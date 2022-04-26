package com.numbervalidator.exercise.controller;

import com.numbervalidator.exercise.dto.Customer;
import com.numbervalidator.exercise.service.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomersContoller.class)
public class CustomerControllerTest {

    private static final String BASE_URL = "/customers/details";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceImpl customerService;


    @Test
    public void testGetCountryAndStateCustomersDetailsWithPaginationInfoThenFail() throws Exception {
        final String countryName = "All";
        final String state = "All";
        final int page = 0;
        final int size = 10;

        given(customerService.getCustomersNumbers(countryName, state, page, size)).willReturn(new PageImpl<>(Collections.emptyList()));

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "?countryName=" + countryName + "&page=" + page + "&size=" + size)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetCountryCustomersDetailsWithPaginationInfoThenSuccess() throws Exception {
        final String countryName = "Morocco";
        final String state = "All";
        final int page = 0;
        final int size = 10;
        Customer customer = new Customer(1l,"Customer Name","(212) 578945632","(212)","Morocco",true);
        List <Customer> customerDto = new ArrayList<>();
        customerDto.add(customer);


        Mockito.when(customerService.getCustomersNumbers(countryName, state, page, size)).thenReturn(new PageImpl<>(customerDto));
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "?countryName=" + countryName + "&page=" + page + "&size=" + size)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetStateCustomersDetailsWithPaginationInfoThenSuccess() throws Exception {
        final String countryName = "All";
        final String state = "valid number";
        final int page = 0;
        final int size = 10;
        Customer customer = new Customer(1l,"Customer Name","(212) 578945632","(212)","Morocco",true);
        List <Customer> customerDto = new ArrayList<>();
        customerDto.add(customer);


        Mockito.when(customerService.getCustomersNumbers(countryName, state, page, size)).thenReturn(new PageImpl<>(customerDto));
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "?state=" + state + "&page=" + page + "&size=" + size)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllCountryStateCustomersDetailsWithPaginationInfoThenSuccess() throws Exception {
        final String countryName = "All";
        final String state = "All";
        final int page = 0;
        final int size = 10;
        Customer customer = new Customer(1l,"Customer Name","(212) 578945632","(212)","Morocco",true);
        Customer secCustomer = new Customer(1l,"Customer Name","(212) 578925632","(212)","Morocco",true);
        List <Customer> customerDto = new ArrayList<>();
        customerDto.add(customer);
        customerDto.add(secCustomer);


        Mockito.when(customerService.getCustomersNumbers(countryName, state, page, size)).thenReturn(new PageImpl<>(customerDto));
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "?countryName=" + countryName + "&state=" + state + "&page=" + page + "&size=" + size)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllCountryStateCustomersDetailsWithPaginationDefaultInfoThenSuccess() throws Exception {
        final String countryName = "All";
        final String state = "All";
        final int page = 0;
        final int size = 5;
        Customer customer = new Customer(1l,"Customer Name","(212) 578945632","(212)","Morocco",true);
        Customer secCustomer = new Customer(1l,"Customer Name","(212) 578925632","(212)","Morocco",true);
        List <Customer> customerDto = new ArrayList<>();
        customerDto.add(customer);
        customerDto.add(secCustomer);


        Mockito.when(customerService.getCustomersNumbers(countryName, state, page, size)).thenReturn(new PageImpl<>(customerDto));
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "?countryName=" + countryName + "&state=" + state)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
