package com.googlepay.dto;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


public class TransactionHistoryDto {

	private int transactionId;
	private String toMobileNumber;
	private String fromMobileNumber;
	private double transactionAmount;
	
	
	public TransactionHistoryDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	public TransactionHistoryDto(int transactionId, String toMobileNumber, String fromMobileNumber,
			double transactionAmount) {
		super();
		this.transactionId = transactionId;
		this.toMobileNumber = toMobileNumber;
		this.fromMobileNumber = fromMobileNumber;
		this.transactionAmount = transactionAmount;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(fromMobileNumber, toMobileNumber, transactionAmount, transactionId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionHistoryDto other = (TransactionHistoryDto) obj;
		return Objects.equals(fromMobileNumber, other.fromMobileNumber)
				&& Objects.equals(toMobileNumber, other.toMobileNumber)
				&& Double.doubleToLongBits(transactionAmount) == Double.doubleToLongBits(other.transactionAmount)
				&& transactionId == other.transactionId;
	}


	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getToMobileNumber() {
		return toMobileNumber;
	}
	public void setToMobileNumber(String toMobileNumber) {
		this.toMobileNumber = toMobileNumber;
	}
	public String getFromMobileNumber() {
		return fromMobileNumber;
	}
	public void setFromMobileNumber(String fromMobileNumber) {
		this.fromMobileNumber = fromMobileNumber;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	
	
	@Override
	public String toString() {
		return "TransactionHistory [transactionId=" + transactionId + ", toMobileNumber=" + toMobileNumber
				+ ", fromMobileNumber=" + fromMobileNumber + ", transactionAmount=" + transactionAmount + "]";
	}
	
	
	
}
