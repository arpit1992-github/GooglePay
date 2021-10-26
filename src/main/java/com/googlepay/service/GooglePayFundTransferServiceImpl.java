package com.googlepay.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlepay.controller.GooglePayFundTransfer;
import com.googlepay.dto.GooglePayFundTransferReq;
import com.googlepay.entity.GooglePayCustomer;
import com.googlepay.entity.TransactionHistory;
import com.googlepay.feignclient.BankClient;
import com.googlepay.repo.CustomerRegistrationRepository;
import com.googlepay.repo.TransactionRepository;



@Service
@Transactional
public class GooglePayFundTransferServiceImpl implements GooglePayFundTransferService {

	private static final Logger logger = LoggerFactory.getLogger(GooglePayFundTransferServiceImpl.class);
	

	@Autowired
	CustomerRegistrationRepository customerRepo;
	
	@Autowired
	private TransactionRepository transRepo;
	@Autowired
	private BankClient bankClient;
	

	@Autowired
	CircuitBreakerFactory circuitBreakerFactory;

    
	public  String getInfo() {
		logger.info("GooglePayFundTransferServiceImpl.getInfo()");
	
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
		logger.info("End GooglePayFundTransferServiceImpl.getInfo()");
		return circuitBreaker.run(() -> bankClient.getFundTransferInfo(), throwable -> getDefaultInfo());
		
	}

	public String getDefaultInfo() {
		logger.info("start GooglePayFundTransferServiceImpl.getDefaultInfo()");
		
		return "Down";
	}

	
	@Override
	public String fundTransfer(GooglePayFundTransferReq fundTransfer) {
		logger.info("start GooglePayFundTransferServiceImpl.fundTransfer()");
		double amount=fundTransfer.getTransactionAmount();
	
		String toMobNumber=fundTransfer.getToMobileNumber();
		String fromMobNumber=fundTransfer.getFromMobileNumber();
		
		if(customerRepo.findByMobileNumber(fromMobNumber)==null) {
			return "From Mobile Number is not registered with GooglePay";
		}
		if(customerRepo.findByMobileNumber(toMobNumber)==null) {
			return "To Mobile Number is not registered with GooglePay";
		}
		
		String fundTransferByMobNumber = bankClient.fundTransferByMobNumber(amount,toMobNumber, fromMobNumber);
	
		if(fundTransferByMobNumber.equals("OK")) {
			TransactionHistory fromTransaction=new TransactionHistory();
			fundTransfer.setTransactionAmount(-amount);
			BeanUtils.copyProperties(fundTransfer, fromTransaction);
			transRepo.save(fromTransaction);
			fundTransfer.setTransactionAmount(amount);
			TransactionHistory totransaction=new TransactionHistory();
			BeanUtils.copyProperties(fundTransfer, totransaction);
			transRepo.save(totransaction);
			return "Transaction Successfull";
		}else {
			return fundTransferByMobNumber;
		}
	}

}
