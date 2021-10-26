package com.googlepay.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.googlepay.dto.TransactionHistoryDto;
import com.googlepay.entity.TransactionHistory;


public interface TransactionRepository extends JpaRepository<TransactionHistory, Serializable> {

	public List<TransactionHistory> findByToMobileNumberOrFromMobileNumber(String toMobileNumber,String fromMobileNumber,Pageable page);
	
}
