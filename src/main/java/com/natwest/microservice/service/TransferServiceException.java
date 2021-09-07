package com.natwest.microservice.service;

public class TransferServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public TransferServiceException(String errorMessage) {
		super(errorMessage);
	}
}