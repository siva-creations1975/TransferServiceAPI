

package com.natwest.microservice.model;

public class TransferRequest {

	public TransferRequest() {}
	
	private TransactionModel transactionModel;

	public TransactionModel getTransactionModel() {
		return transactionModel;
	}

	public void setTransactionModel(TransactionModel transactionModel) {
		this.transactionModel = transactionModel;
	}

	public TransferRequest(TransactionModel transactionModel) {
		super();
		this.transactionModel = transactionModel;
	}
	
}