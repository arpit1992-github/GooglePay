package com.googlepay.service;

import com.googlepay.dto.CustomerRegistrationReqDTO;

public interface GooglePayRegistrationService {

	public String customerRegistration(CustomerRegistrationReqDTO customerReq);
	public  String getInfo();
}
