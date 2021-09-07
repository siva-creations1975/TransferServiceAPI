package com.natwest.microservice.service;

import com.natwest.microservice.model.TransferRequest;
import com.natwest.microservice.model.TransferResponse;

/**
 * An interface for transfer request service.
 */
public interface ITransferService {

	/**
	 * A method which is responsible to do the funds transfer from source account to destination account
	 *
	 * @param transferRequest request
	 * @return TransferResponse
	 * @exception TransferServiceException
	 */
	TransferResponse transfer(TransferRequest transferRequest) throws TransferServiceException;
}
