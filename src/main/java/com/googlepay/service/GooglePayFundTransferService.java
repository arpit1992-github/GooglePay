package com.googlepay.service;

import com.googlepay.dto.GooglePayFundTransferReq;

public interface GooglePayFundTransferService {

	public String fundTransfer(GooglePayFundTransferReq fundTransfer);
	public  String getInfo();
}
