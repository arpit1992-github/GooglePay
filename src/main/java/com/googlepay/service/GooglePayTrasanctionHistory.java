package com.googlepay.service;

import com.googlepay.dto.TransactionResponseDto;


public interface GooglePayTrasanctionHistory {

	public TransactionResponseDto getTransactionHistory(String mobNumber);
}
