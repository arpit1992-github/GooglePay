package com.googlepay.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.googlepay.dto.UPIFundTransferRespDto;

@FeignClient(value = "bank-service", url = "http://localhost:9090/bank")
public interface BankClient {
	
	@GetMapping("/upi/checkMobStatus/{mobNumber}")
	public boolean checkUserRegMobNumber(@PathVariable String mobNumber);
	
	
	@PostMapping("/upifundTransfer/transaction")
	public String fundTransferByMobNumber(@RequestParam("amount")double amount,@RequestParam("toMobileNumber")String toMobileNumber,@RequestParam("fromMobileNumber")String fromMobileNumber);

	@GetMapping("/upi/port")
	public String getRegInfo();
	
	@GetMapping("/upifundTransfer/port")
	public String getFundTransferInfo();

}
