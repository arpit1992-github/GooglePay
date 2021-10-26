package com.googlepay.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.googlepay.dto.CustomerRegistrationReqDTO;
import com.googlepay.service.GooglePayRegistrationService;

@RestController
@Validated
@RequestMapping("/googlepay")
public class GooglePayCustomerRegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(GooglePayCustomerRegistrationController.class);
	
	@Autowired
	GooglePayRegistrationService userService;
	
	@PostMapping("/registration")
	public String customerRegistration(@Valid @RequestBody CustomerRegistrationReqDTO customer) {
		logger.info("start GooglePayCustomerRegistrationController.customerRegistration()");
		String info = userService.getInfo();
		if(info!=null && info.equals("down")) {
		return "Bank-Service is down, Please try after some time.";
		}
		String message =userService.customerRegistration(customer);
		
		logger.info("End GooglePayCustomerRegistrationController.customerRegistration()");
		return message;
	}
}
