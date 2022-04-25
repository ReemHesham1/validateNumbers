package com.numbervalidator.exercise.service;


import com.numbervalidator.exercise.countryprocessor.CountryFetcher;
import com.numbervalidator.exercise.datamodel.CustomerEntity;
import com.numbervalidator.exercise.dto.Customer;
import com.numbervalidator.exercise.exceptions.InvalidCountryNameException;
import com.numbervalidator.exercise.exceptions.NoCustomersFoundException;
import com.numbervalidator.exercise.mapper.Mapper;
import com.numbervalidator.exercise.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepo;
    @Mock
    private CountryFetcher countryFetcher;
    @Mock
    private Mapper mapper;


    @Test
    public void testCountryAndStateCustomersDetailsWithPaginationInfoThenSuccess() throws NoCustomersFoundException, InvalidCountryNameException {

        CustomerEntity customerEntity = createCustomer(3L, "Morocco", "(212) 578945632");
        Mockito.when(customerRepo.findByPhoneStartsWith("(212)")).thenReturn(List.of(customerEntity));
        Mockito.when(mapper.mapToDto(customerEntity)).thenReturn(createCustomerDto(customerEntity,"(212)","Morocco",true));
        Mockito.when(countryFetcher.getCodeFromCountry("Morocco")).thenReturn("(212)");

        Page<Customer> response = customerService.getCustomersNumbers("Morocco", "valid number", 0, 10);

        assertEquals(1L, response.getTotalElements());
    }

    @Test
    public void testCountryAndStateCustomersDetailsWithPaginationInfoAndNoCustomerDataThenFail() throws NoCustomersFoundException, InvalidCountryNameException {

        CustomerEntity customerEntity = createCustomer(3L, "Morocco", "(212) 578945632");
        Mockito.when(customerRepo.findByPhoneStartsWith("(212)")).thenReturn(Collections.emptyList());

        assertThrows(NoCustomersFoundException.class,
                () -> {
                    customerService.getCustomersNumbers("Morocco", "valid number", 0, 10);;
                });
    }
// ????????
    @Test
    public void testCountryAndStateCustomersDetailsWithPaginationInfoWithInvalidCountryThenSuccess() throws NoCustomersFoundException, InvalidCountryNameException {

        Mockito.when(countryFetcher.getCodeFromCountry("WrongCountryName")).thenThrow();

        assertThrows(InvalidCountryNameException.class,
                () -> {
                    customerService.getCustomersNumbers("WrongCountryName", "valid number", 0, 10);;
                });
    }

    @Test
    public void testCountryCustomersDetailsWithPaginationInfoThenSuccess() throws NoCustomersFoundException, InvalidCountryNameException {

        CustomerEntity customerEntityValid = createCustomer(3L, "Morocco", "(212) 578945632");
        CustomerEntity customerEntityInValid = createCustomer(4L, "Morocco", "(212) 578945");
        Mockito.when(customerRepo.findByPhoneStartsWith("(212)", PageRequest.of(0, 10))).thenReturn(new PageImpl<>(List.of(customerEntityValid,customerEntityInValid)));
        Mockito.when(mapper.mapToDto(customerEntityValid)).thenReturn(createCustomerDto(customerEntityValid,"(212)","Morocco",true));
        Mockito.when(mapper.mapToDto(customerEntityInValid)).thenReturn(createCustomerDto(customerEntityInValid,"(212)","Morocco",false));
        Mockito.when(countryFetcher.getCodeFromCountry("Morocco")).thenReturn("(212)");

        Page<Customer> response = customerService.getCustomersNumbers("Morocco", "all", 0, 10);

        assertEquals(2L, response.getTotalElements());
    }

    @Test
    public void testStateCustomersDetailsWithPaginationInfoThenSuccess() throws NoCustomersFoundException, InvalidCountryNameException {

        CustomerEntity customerEntityValid = createCustomer(3L, "Morocco", "(212) 578945632");
        CustomerEntity customerEntityInValid = createCustomer(4L, "Morocco", "(212) 578945");
        Mockito.when(customerRepo.findAll()).thenReturn(List.of(customerEntityValid,customerEntityInValid));
        Mockito.when(customerRepo.findByPhoneStartsWith("(212)")).thenReturn(List.of(customerEntityValid,customerEntityInValid));
        Mockito.when(mapper.mapToDto(customerEntityValid)).thenReturn(createCustomerDto(customerEntityValid,"(212)","Morocco",true));
        Mockito.when(mapper.mapToDto(customerEntityInValid)).thenReturn(createCustomerDto(customerEntityInValid,"(212)","Morocco",false));
        Mockito.when(countryFetcher.getCodeFromCountry("Morocco")).thenReturn("(212)");

        Page<Customer> response = customerService.getCustomersNumbers("all", "valid number", 0, 10);

        assertEquals(1L, response.getTotalElements());
    }

    @Test
    public void testAllCustomersDetailsWithPaginationInfoThenSuccess() throws NoCustomersFoundException, InvalidCountryNameException {

        Pageable pageable = PageRequest.of(0, 10);
        Page<CustomerEntity> customerPage = new PageImpl<>(createCustomers(), pageable, 10);

        Mockito.when(customerRepo.findAll(pageable)).thenReturn(customerPage);

        Page<Customer> response = customerService.getCustomersNumbers("all", "all", 0, 10);

        assertEquals(10L, response.getTotalElements());
    }


    private List<CustomerEntity> createCustomers() {
        List<CustomerEntity> customersEntity = new ArrayList<>();
//      valid Customers
        customersEntity.add(createCustomer(1L, "Cameroon", "(237) 27894561"));
        customersEntity.add(createCustomer(2L, "Ethiopia", "(251) 178965412"));
        customersEntity.add(createCustomer(3L, "Morocco", "(212) 578945632"));
        customersEntity.add(createCustomer(4L, "Mozambique", "(258) 245632178"));
        customersEntity.add(createCustomer(5L, "Uganda", "(256) 123456789"));
//      invalid Customers
        customersEntity.add(createCustomer(6L, "Cameroon", "(247) 27894561"));
        customersEntity.add(createCustomer(7L, "Ethiopia", "(271) 178965412"));
        customersEntity.add(createCustomer(8L, "Morocco", "(215) 578945632"));
        customersEntity.add(createCustomer(9L, "Mozambique", "(259) 245632178"));
        customersEntity.add(createCustomer(10L, "Uganda", "(277) 123456789"));
        return customersEntity;
    }

    private CustomerEntity createCustomer(Long id, String countryName, String phone) {
        return new CustomerEntity(id, countryName + " Customer", phone);
    }

    private Customer createCustomerDto(CustomerEntity customerEntity, String countryCode, String countryName, boolean isValid) {
        return new Customer(customerEntity.getId(), customerEntity.getName(), customerEntity.getPhone(), countryCode, countryName, isValid);
    }
}
