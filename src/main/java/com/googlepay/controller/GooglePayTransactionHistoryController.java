package com.googlepay.controller;


import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.googlepay.dto.TransactionResponseDto;
import com.googlepay.service.GooglePayFundTransferServiceImpl;
import com.googlepay.service.GooglePayTrasanctionHistory;

@RestController()
@Validated
@RequestMapping("/transactionHistory")
public class GooglePayTransactionHistoryController {

	private static final Logger logger = LoggerFactory.getLogger(GooglePayTransactionHistoryController.class);
	
	
	@Autowired
	GooglePayTrasanctionHistory service;
	
	@GetMapping("/transactions")
	public ResponseEntity<TransactionResponseDto>  trasactionHistory( @Size(min=10,max=10 ,message="Mobile Number should have 10 digit") @Positive(message="Enter valid mobile number") @RequestParam String mobNumber) {
		logger.info("Inside GooglePayTransactionHistoryController.trasactionHistory()");
		TransactionResponseDto transactionHistory = service.getTransactionHistory(mobNumber);
		if(transactionHistory.getList()==null) {
			return new ResponseEntity<TransactionResponseDto>(transactionHistory,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<TransactionResponseDto>(transactionHistory,HttpStatus.OK);
	}
}
