package com.googlepay.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.googlepay.dto.CustomerRegistrationReqDTO;
import com.googlepay.entity.GooglePayCustomer;
import com.googlepay.feignclient.BankClient;
import com.googlepay.repo.CustomerRegistrationRepository;

@ExtendWith(SpringExtension.class)
public class GooglePayRegistrationServiceImplTest {
	
	@InjectMocks
	GooglePayRegistrationServiceImpl customerServiceImpl;
	
	@Mock
    CustomerRegistrationRepository customerRepo;
	
	@Mock
	BankClient bankClient;
	
	@Test
	void testCutomerRegitration() {
		
		Mockito.when(bankClient.checkUserRegMobNumber("9044018011")).thenReturn(true);
		
		Mockito.when(customerRepo.findByMobileNumber("9044018011")).thenReturn(null);
		
		GooglePayCustomer  customer =new GooglePayCustomer ();
		CustomerRegistrationReqDTO customerReq=new CustomerRegistrationReqDTO();
		customerReq.setFirstName("Arpit");
		customerReq.setLastName("M");
		customerReq.setMobileNumber("9044018011");
		Mockito.when(customerRepo.save(customer)).thenReturn(null);
		
		String customerRegistration = customerServiceImpl.customerRegistration(customerReq);
		System.out.println(customerRegistration);
		assertEquals(customerRegistration,"Registration Successful ! enjoy your Google Pay ");
	}
}
