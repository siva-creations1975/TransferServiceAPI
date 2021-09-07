package com.natwest.microservices.test.client;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.natwest.microservice.TransferServiceApp;
import com.natwest.microservice.model.TransferRequest;
import com.natwest.microservice.model.TransferResponse;
import com.natwest.microservice.service.TransferServiceConstants;
import com.natwest.microservices.test.base.TransferServiceTestBase;
import com.natwest.microservices.test.base.TransferServiceTestHelper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransferServiceApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferServiceRestTemplateClientFailTest extends TransferServiceTestBase {
	private String errorMessage = "errorMessage";

	@Test
	public void shouldFailWhenInsufficientBalanceInSourceAccountWhenTransferHappens() throws Exception {
		// Given
		TransferRequest transferRequest = TransferServiceTestHelper.createTransferRequestWithDefaultTransferAmount();
		// Debit 100 from Source and Credit 100 to Destination Account
		transferRequest.getTransactionModel()
				.setAmount(TransferServiceConstants.DEFAULT_SOURCE_ACCOUNT_BALANCE.add(new BigDecimal(100)));
		String errorResponse = "";

		// When
		try {
			invokeTransferServiceAPI(transferRequest);
		} catch (HttpClientErrorException e) {
			errorResponse = e.getResponseBodyAsString();
		}

		// Then
		verifyUnsuccessfulTransfer(TransferServiceConstants.NO_SUFFICIENT_FUNDS_IN_THE_ACCOUNT, errorResponse,
				errorMessage);
	}

	@Test
	public void shouldFailWhenDestinationAccountIsSameAsSourceAccount() throws Exception {
		// Given
		TransferRequest transferRequest = TransferServiceTestHelper.createTransferRequestWithDefaultTransferAmount();
		transferRequest.getTransactionModel().setDestAccountNo(transferRequest.getTransactionModel().getSrcAccountNo());
		String errorResponse = "";

		// When
		try {
			invokeTransferServiceAPI(transferRequest);
		} catch (HttpClientErrorException e) {
			errorResponse = e.getResponseBodyAsString();
		}

		// Then
		verifyUnsuccessfulTransfer(TransferServiceConstants.DESTINATION_ACCOUNT_SHOULD_BE_DIFFERENT, errorResponse,
				errorMessage);
	}

	@Test
	public void shouldFailWhenSourceAccountIsNotFound() throws Exception {
		// Given
		TransferRequest transferRequest = TransferServiceTestHelper.createTransferRequestWithDefaultTransferAmount();
		// Set source account number which is not in default accounts - 1003L
		transferRequest.getTransactionModel().setSrcAccountNo(1003L);
		String errorResponse = "";

		// When
		try {
			invokeTransferServiceAPI(transferRequest);
		} catch (HttpClientErrorException e) {
			errorResponse = e.getResponseBodyAsString();
		}

		// Then
		verifyUnsuccessfulTransfer(TransferServiceConstants.SOURCE_ACCOUNT_IS_NOT_FOUND, errorResponse, errorMessage);
	}

	@Test
	public void shouldFailWhenDestinationAccountIsNotFound() throws Exception {
		// Given
		TransferRequest transferRequest = TransferServiceTestHelper.createTransferRequestWithDefaultTransferAmount();
		// Set destination account number which is not in default accounts - 1003L
		transferRequest.getTransactionModel().setDestAccountNo(1003L);
		String errorResponse = "";

		// When
		try {
			invokeTransferServiceAPI(transferRequest);
		} catch (HttpClientErrorException e) {
			errorResponse = e.getResponseBodyAsString();
		}

		// Then
		verifyUnsuccessfulTransfer(TransferServiceConstants.DESTINATION_ACCOUNT_IS_NOT_FOUND, errorResponse,
				errorMessage);
	}
}
