package com.googlepay.dto;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


public class CustomerRegistrationReqDTO {

	@NotNull(message="First Name is maindatory *")
	@NotEmpty(message="First Name is maindatory *")
	private String firstName;
	@NotNull(message="First Name is maindatory *")
	@NotEmpty(message="Last Name is maindatory *")
	private String lastName;
	
	@Pattern(regexp="(^$|[0-9]{10})" ,message ="Input valid Mobile number *")
	@Size(min=10 ,max=10,message="Mobile Number should have 10 digits")
	private String mobileNumber;
	
	public CustomerRegistrationReqDTO() {
		// TODO Auto-generated constructor stub
	}

	public CustomerRegistrationReqDTO(String firstName, String lastName, String mobileNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, mobileNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerRegistrationReqDTO other = (CustomerRegistrationReqDTO) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(mobileNumber, other.mobileNumber);
	}

	@Override
	public String toString() {
		return "CustomerRegistrationReqDTO [firstName=" + firstName + ", lastName=" + lastName + ", mobileNumber="
				+ mobileNumber + "]";
	}
	
	
}
