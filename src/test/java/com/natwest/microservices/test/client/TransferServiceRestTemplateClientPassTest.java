package com.natwest.microservices.test.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.natwest.microservice.TransferServiceApp;
import com.natwest.microservice.model.TransferRequest;
import com.natwest.microservice.model.TransferResponse;
import com.natwest.microservices.test.base.TransferServiceTestBase;
import com.natwest.microservices.test.base.TransferServiceTestHelper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransferServiceApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferServiceRestTemplateClientPassTest extends TransferServiceTestBase {

	@Test
	public void doSuccessfulTransfer() throws Exception {
		// Given
		TransferRequest transferRequest = TransferServiceTestHelper.createTransferRequestWithDefaultTransferAmount();

		// When
		TransferResponse transferResponse = invokeTransferServiceAPI(transferRequest);

		// Then
		verifySuccessfulTransfer(transferRequest, transferResponse);
	}
}
