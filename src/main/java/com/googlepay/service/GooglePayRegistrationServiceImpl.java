package com.googlepay.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;

import org.springframework.stereotype.Service;

import com.googlepay.controller.GooglePayCustomerRegistrationController;
import com.googlepay.dto.CustomerRegistrationReqDTO;
import com.googlepay.entity.GooglePayCustomer;
import com.googlepay.feignclient.BankClient;
import com.googlepay.repo.CustomerRegistrationRepository;





@Service
public class GooglePayRegistrationServiceImpl implements GooglePayRegistrationService {

	private static final Logger logger = LoggerFactory.getLogger(GooglePayRegistrationServiceImpl.class);
	
	
	@Autowired
	private CustomerRegistrationRepository customerRepo;
	
	@Autowired
	private  BankClient bankClient;

	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;

    
	public  String getInfo() {
		logger.info("start GooglePayRegistrationServiceImpl.getInfo()");
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
		logger.info("start GooglePayRegistrationServiceImpl.getInfo()");
		return circuitBreaker.run(() -> bankClient.getRegInfo(), throwable -> getDefaultInfo());
	}

	public String getDefaultInfo() {
		
		return "down";
	}

	
	
	@Override
	public String customerRegistration(CustomerRegistrationReqDTO customerReq) {
		logger.info("start GooglePayRegistrationServiceImpl.customerRegistration()");
		boolean isUserExist = bankClient.checkUserRegMobNumber(customerReq.getMobileNumber());

		if (isUserExist) {
			GooglePayCustomer customer = new GooglePayCustomer();

			BeanUtils.copyProperties(customerReq, customer);
			GooglePayCustomer isCustExist = customerRepo.findByMobileNumber(customerReq.getMobileNumber());
			if (isCustExist!=null) {
				return "customer all Ready register with Google Pay";
			} else {
				customerRepo.save(customer);
			}
		} else {
			return "Given Mobile Number is not associated with any account";
		}
		return "Registration Successful ! enjoy your Google Pay ";
	}
	
	

}
