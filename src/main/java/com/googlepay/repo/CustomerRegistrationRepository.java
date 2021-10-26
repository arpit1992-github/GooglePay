package com.googlepay.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.googlepay.entity.GooglePayCustomer;

public interface CustomerRegistrationRepository extends JpaRepository<GooglePayCustomer, Serializable>{

	GooglePayCustomer findByMobileNumber(String mobileNumber);

}
