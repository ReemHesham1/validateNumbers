package com.numbervalidator.exercise.repository;


import com.numbervalidator.exercise.datamodel.CustomerEntity;
import com.numbervalidator.exercise.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    public void whenFindByPhoneStartsWithThenReturnCustomers() {
        // given
        CustomerEntity customerEntity = new CustomerEntity(1L,"customerName","(212) 6007989253");
        entityManager.persist(customerEntity);
        entityManager.flush();

        // when
        List<CustomerEntity> found = customerRepository.findByPhoneStartsWith("(212)");

        // then
        assertThat(found.get(0).getName())
                .isEqualTo(customerEntity.getName());
    }
    @Test
    public void whenFindByPhoneStartsWithThenReturnPageCustomers() {
        // given
        CustomerEntity customerEntity = new CustomerEntity(1L,"customerName","(212) 6007989253");
        entityManager.persist(customerEntity);
        entityManager.flush();

        // when
        Page<CustomerEntity> found = customerRepository.findByPhoneStartsWith("(212)", PageRequest.of(0, 1));

        // then
        assertThat(found.get().findFirst().get().getName())
                .isEqualTo(customerEntity.getName());
    }

    @Test
    public void whenFindAllThenReturnPageCustomers() {
        // given
        CustomerEntity customerEntity = new CustomerEntity(1L,"customerName","(212) 6007989253");
        entityManager.persist(customerEntity);
        entityManager.flush();

        // when
        Page<CustomerEntity> found = customerRepository.findAll(PageRequest.of(0, 1));

        // then
        assertThat(found.get().findFirst().get().getName())
                .isEqualTo(customerEntity.getName());
    }

    @Test
    public void whenFindAllThenReturnCustomers() {
        // given
        CustomerEntity customerEntity = new CustomerEntity(1L,"customerName","(212) 6007989253");
        entityManager.persist(customerEntity);
        entityManager.flush();

        // when
        List<CustomerEntity> found = customerRepository.findAll();

        // then
        assertThat(found.get(0).getName())
                .isEqualTo(customerEntity.getName());
    }
}
