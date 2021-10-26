package com.googlepay.service;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.googlepay.controller.GooglePayTransactionHistoryController;
import com.googlepay.dto.TransactionHistoryDto;
import com.googlepay.dto.TransactionResponseDto;
import com.googlepay.entity.GooglePayCustomer;
import com.googlepay.entity.TransactionHistory;
import com.googlepay.repo.CustomerRegistrationRepository;
import com.googlepay.repo.TransactionRepository;

@Service
public class GooglePayTrasanctionHistoryImpl implements GooglePayTrasanctionHistory {

	private static final Logger logger = LoggerFactory.getLogger(GooglePayTrasanctionHistoryImpl.class);
	
	
	@Autowired
	TransactionRepository trans;
	@Autowired
	CustomerRegistrationRepository userRepo;
	
	
	@Override
	public TransactionResponseDto getTransactionHistory(String mobNumber) {
		logger.info("Inside GooglePayTrasanctionHistoryImpl.getTransactionHistory()");
		TransactionResponseDto transactions=new TransactionResponseDto();
		
		GooglePayCustomer user = userRepo.findByMobileNumber(mobNumber);
		if(user!=null) {
			logger.info("user found !");
		List<TransactionHistoryDto> dtoList=new ArrayList<>();
		
		Pageable pagination=PageRequest.of(0, 5);
		List<TransactionHistory> list = trans.findByToMobileNumberOrFromMobileNumber(mobNumber, mobNumber, pagination);
		logger.info("pagination done !");
		for(TransactionHistory transHistory:list) {
			TransactionHistoryDto dto=new TransactionHistoryDto();
		BeanUtils.copyProperties(transHistory, dto);
		dtoList.add(dto);
		}
		transactions.setList(dtoList);
		transactions.setMessage("OK");
		return transactions;
		}else {
			logger.info("user not found !");
			transactions.setList(null);
			transactions.setMessage("Please Enter Registered Mobile Number");
			return transactions;
		}
	}
	
	
}
