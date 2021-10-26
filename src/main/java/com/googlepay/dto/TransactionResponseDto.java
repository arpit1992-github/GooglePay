package com.googlepay.dto;

import java.util.List;
import java.util.Objects;

public class TransactionResponseDto {

	private String message ;
	private List<TransactionHistoryDto> list;
	
	public TransactionResponseDto() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<TransactionHistoryDto> getList() {
		return list;
	}

	public void setList(List<TransactionHistoryDto> list) {
		this.list = list;
	}

	public TransactionResponseDto(String message, List<TransactionHistoryDto> list) {
		super();
		this.message = message;
		this.list = list;
	}

	@Override
	public int hashCode() {
		return Objects.hash(list, message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionResponseDto other = (TransactionResponseDto) obj;
		return Objects.equals(list, other.list) && Objects.equals(message, other.message);
	}

	@Override
	public String toString() {
		return "TransactionResponseDto [message=" + message + ", list=" + list + "]";
	}
	
	
}
