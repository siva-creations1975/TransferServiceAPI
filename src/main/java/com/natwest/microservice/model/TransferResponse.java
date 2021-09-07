package com.natwest.microservice.model;

public class TransferResponse {

	private AccountModel srcAccountModel;
	private AccountModel destAccountModel;
	private String errorMessage;

	public TransferResponse() {
	}

	public TransferResponse(AccountModel srcAccountModel, AccountModel destAccountModel, String errorMessage) {
		this.srcAccountModel = srcAccountModel;
		this.destAccountModel = destAccountModel;
		this.errorMessage = errorMessage;
	}

	public AccountModel getSrcAccountModel() {
		return srcAccountModel;
	}

	public void setSrcAccountModel(AccountModel srcAccountModel) {
		this.srcAccountModel = srcAccountModel;
	}

	public AccountModel getDestAccountModel() {
		return destAccountModel;
	}

	public void setDestAccountModel(AccountModel destAccountModel) {
		this.destAccountModel = destAccountModel;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static TransferResponse withErrorMessage(String errorMessage) {
		return new TransferResponse(null, null, errorMessage);
	}

	public static TransferResponse withAccountDetails(AccountModel srcAccountModel, AccountModel destAccountModel) {
		return new TransferResponse(srcAccountModel, destAccountModel, null);
	}
}