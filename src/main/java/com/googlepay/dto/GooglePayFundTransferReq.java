package com.googlepay.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class GooglePayFundTransferReq {

	@Size(min=10 ,max=10 ,message="Mobile Number should have 10 digits")
	private String toMobileNumber;
	@Size(min=10 ,max=10 ,message="Mobile Number should have 10 digits")
	private String fromMobileNumber;
	@DecimalMin(value="1.0" ,message="Enter valid amount *")
	@Positive(message = "invalid amount !")
	private double transactionAmount ;
	
	public GooglePayFundTransferReq(String toMobileNumber, String fromMobileNumber, double transactionAmount) {
		super();
		this.toMobileNumber = toMobileNumber;
		this.fromMobileNumber = fromMobileNumber;
		this.transactionAmount = transactionAmount;
	}
	public GooglePayFundTransferReq() {
		// TODO Auto-generated constructor stub
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
	
	
	
	
}
