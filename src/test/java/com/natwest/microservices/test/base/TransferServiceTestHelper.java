package com.natwest.microservices.test.base;

import java.math.BigDecimal;

import com.natwest.microservice.model.AccountModel;
import com.natwest.microservice.model.TransactionModel;
import com.natwest.microservice.model.TransferRequest;
import com.natwest.microservice.model.TransferResponse;
import com.natwest.microservice.service.TransferServiceConstants;

public class TransferServiceTestHelper {

	public static TransferRequest createTransferRequestWithDefaultTransferAmount() {

		BigDecimal amount = new BigDecimal(100);
		TransferRequest transferRequest = new TransferRequest();
		TransactionModel transactionModel = new TransactionModel(TransferServiceConstants.DEFAULT_SOURCE_ACCOUNT_NUMBER,
				TransferServiceConstants.DEFAULT_DESTINATION_ACCOUNT_NUMBER, amount);
		transferRequest.setTransactionModel(transactionModel);
		return transferRequest;
	}

	public static TransferResponse createTransferResponse() {
		TransferRequest transferRequest = createTransferRequestWithDefaultTransferAmount();
		AccountModel srcAccountModel = new AccountModel(transferRequest.getTransactionModel().getSrcAccountNo(),
				new BigDecimal(1000));
		AccountModel destAccountModel = new AccountModel(transferRequest.getTransactionModel().getDestAccountNo(),
				new BigDecimal(1000));
		TransferResponse transferResponse = new TransferResponse(srcAccountModel, destAccountModel, "");
		return transferResponse;
	}
}
