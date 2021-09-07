package com.natwest.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.natwest.microservice.model.TransferRequest;
import com.natwest.microservice.model.TransferResponse;
import com.natwest.microservice.transaction.IProcessTransaction;

@Component
public class TransferServiceImpl implements ITransferService {

	@Autowired
	private IProcessTransaction processTransaction;

	@Override
	public TransferResponse transfer(TransferRequest transferRequest) throws TransferServiceException {
		return processTransaction.processTransfer(transferRequest.getTransactionModel().getSrcAccountNo(),
				transferRequest.getTransactionModel().getDestAccountNo(),
				transferRequest.getTransactionModel().getAmount());

	}
}
