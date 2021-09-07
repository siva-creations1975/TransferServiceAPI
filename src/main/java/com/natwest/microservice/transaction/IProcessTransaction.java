package com.natwest.microservice.transaction;

import java.math.BigDecimal;

import com.natwest.microservice.model.TransferResponse;
import com.natwest.microservice.service.TransferServiceException;

public interface IProcessTransaction {

	/**
	 * An interface method which is responsible to process the transfer request
	 * transaction and sends the amount from source account to destination account
	 *
	 * @param srcAccountNo  source account number
	 * @param destAccountNo destination account number
	 * @param amount        amount
	 * @return TransferResponse
	 * @exception TransferServiceException
	 */
	TransferResponse processTransfer(Long srcAccountNo, Long destAccountNo, BigDecimal amount)
			throws TransferServiceException;
}
