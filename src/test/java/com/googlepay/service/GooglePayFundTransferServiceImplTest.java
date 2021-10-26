package com.googlepay.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.googlepay.dto.GooglePayFundTransferReq;
import com.googlepay.entity.GooglePayCustomer;
import com.googlepay.feignclient.BankClient;
import com.googlepay.repo.CustomerRegistrationRepository;
import com.googlepay.repo.TransactionRepository;

@ExtendWith(SpringExtension.class)
public class GooglePayFundTransferServiceImplTest {

	@InjectMocks
	GooglePayFundTransferServiceImpl serviceImpl;
	
	@Mock
	CustomerRegistrationRepository customerRepo;
	
	@Mock
	TransactionRepository transRepo;

	@MockBean
	BankClient bankClient;
	
	private static GooglePayFundTransferReq fundTransfer=null;
	private static GooglePayCustomer customer=null;
	
	@BeforeAll
	static void start() {
		fundTransfer=new GooglePayFundTransferReq();
		customer=new GooglePayCustomer();
	}
	
	@Test
	public void testFundTransfer() {
		
		fundTransfer.setFromMobileNumber("9044018011");
		fundTransfer.setToMobileNumber("8400477432");
		fundTransfer.setTransactionAmount(2000.00);
		
		customer.setCustomerId(1);
		customer.setFirstName("Arpit");
		customer.setLastName("Malviya");
		customer.setMobileNumber("9044018011");
		Mockito.when(customerRepo.findByMobileNumber("9044018011"))
		.thenReturn(customer);
		
		customer.setCustomerId(2);
		customer.setFirstName("Buvan");
		customer.setLastName("shirma");
		customer.setMobileNumber("8400477432");
		Mockito.when(customerRepo.findByMobileNumber("8400477432"))
		.thenReturn(customer);
		
		Mockito.when(bankClient.fundTransferByMobNumber(1000.00,"8400477432","9044018011"))
		.thenReturn("OK");
		
		    String resultString = serviceImpl.fundTransfer(fundTransfer);
		    assertEquals("Transaction Successfull", resultString);
	}
	
	@AfterAll
	static void end() {
		fundTransfer=null;
		customer=null;
	}
}
 