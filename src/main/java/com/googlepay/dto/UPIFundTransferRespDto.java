package com.googlepay.dto;

import java.util.Objects;

public class UPIFundTransferRespDto {

	private double toAccountAmount;
	private double fromAccountAmount;
	
	public UPIFundTransferRespDto() {
		// TODO Auto-generated constructor stub
	}

	public double getToAccountAmount() {
		return toAccountAmount;
	}

	public void setToAccountAmount(double toAccountAmount) {
		this.toAccountAmount = toAccountAmount;
	}

	public double getFromAccountAmount() {
		return fromAccountAmount;
	}

	public void setFromAccountAmount(double fromAccountAmount) {
		this.fromAccountAmount = fromAccountAmount;
	}

	public UPIFundTransferRespDto(double toAccountAmount, double fromAccountAmount) {
		super();
		this.toAccountAmount = toAccountAmount;
		this.fromAccountAmount = fromAccountAmount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fromAccountAmount, toAccountAmount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UPIFundTransferRespDto other = (UPIFundTransferRespDto) obj;
		return Double.doubleToLongBits(fromAccountAmount) == Double.doubleToLongBits(other.fromAccountAmount)
				&& Double.doubleToLongBits(toAccountAmount) == Double.doubleToLongBits(other.toAccountAmount);
	}

	@Override
	public String toString() {
		return "FundTransferRespDto [toAccountAmount=" + toAccountAmount + ", fromAccountAmount=" + fromAccountAmount
				+ "]";
	}
	
	
}
