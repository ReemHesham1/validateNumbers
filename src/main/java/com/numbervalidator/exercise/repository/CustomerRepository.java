package com.numbervalidator.exercise.repository;


import com.numbervalidator.exercise.datamodel.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Transactional
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Page<CustomerEntity> findByPhoneStartsWith(@Param("phone") String phone, Pageable pageable);

    List<CustomerEntity> findByPhoneStartsWith(@Param("phone") String phone);
}
