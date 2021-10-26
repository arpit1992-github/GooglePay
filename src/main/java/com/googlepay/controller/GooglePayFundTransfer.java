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

import com.googlepay.dto.GooglePayFundTransferReq;
import com.googlepay.service.GooglePayFundTransferService;
import com.googlepay.service.GooglePayRegistrationServiceImpl;

@RestController
@Validated
@RequestMapping("/transaction")
public class GooglePayFundTransfer {

	private static final Logger logger = LoggerFactory.getLogger(GooglePayFundTransfer.class);
	
	
	
	@Autowired
	GooglePayFundTransferService transferService;
	
	@PostMapping("/fundTrasction")
	public String fundTransfer(@Valid @RequestBody GooglePayFundTransferReq fundTransferRequest) {
		logger.info("Start GooglePayFundTransfer.fundTransfer()");
		String info =transferService.getInfo();
		if(info!=null && info.equals("down")) {
		return "Bank-Service is down, Please try after some time.";
		}
		
		String message = transferService.fundTransfer(fundTransferRequest);
		logger.info("end GooglePayFundTransfer.fundTransfer())");
		
		return message;
	}
}
