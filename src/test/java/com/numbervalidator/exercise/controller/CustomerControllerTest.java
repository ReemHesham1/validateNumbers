package com.numbervalidator.exercise.controller;

import com.numbervalidator.exercise.dto.Customer;
import com.numbervalidator.exercise.service.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
        final String countryName = "all";
        final String state = "all";
        final int page = 0;
        final int size = 10;

        given(customerService.getCustomersNumbers(countryName, state, page, size)).willReturn(new PageImpl<>(Collections.emptyList()));

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "?countryName=" + countryName + "&page=" + page + "&size=" + size)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetCountryAndStateCustomersDetailsWithPaginationInfoThenSuccess() throws Exception {
        final String countryName = "all";
        final String state = "all";
        final int page = 0;
        final int size = 10;
        final Customer customer = Customer.builder()
                .id(1L)
                .customerName("Customer Name")
                .phoneNumber("(212) 578945632")
                .countryCode("(212)")
                .countryName("Morocco")
                .isValid(true)
                .build();
        given(customerService.getCustomersNumbers(countryName, state, page, size)).willReturn(new PageImpl<>(List.of(customer)));

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "?countryName=" + countryName + "&page=" + page + "&size=" + size)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
