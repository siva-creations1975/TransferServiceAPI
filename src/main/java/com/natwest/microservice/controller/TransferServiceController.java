package com.natwest.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.natwest.microservice.model.TransferRequest;
import com.natwest.microservice.model.TransferResponse;
import com.natwest.microservice.service.ITransferService;
import com.natwest.microservice.service.TransferServiceException;

@RestController
@RequestMapping("/ms/app/v1/")
public class TransferServiceController {

	@Autowired
	private ITransferService transferService;

	/**
	 * A method which is responsible to receive the transfer request and process
	 * transfer transactions.
	 *
	 * @param transferRequest transferRequest
	 * @return ResponseEntity with TransferResponse
	 * @exception TransferServiceException
	 */
	@PostMapping("/transfer-service")
	public ResponseEntity<TransferResponse> transfer(@RequestBody TransferRequest transferRequest)
			throws TransferServiceException {
		return ResponseEntity.ok(transferService.transfer(transferRequest));
	}
}
