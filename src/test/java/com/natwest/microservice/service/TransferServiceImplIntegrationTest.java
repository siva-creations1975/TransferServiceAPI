package com.natwest.microservice.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.natwest.microservice.TransferServiceApp;
import com.natwest.microservice.model.TransferRequest;
import com.natwest.microservice.model.TransferResponse;
import com.natwest.microservice.transaction.IProcessTransaction;
import com.natwest.microservices.test.base.TransferServiceTestBase;
import com.natwest.microservices.test.base.TransferServiceTestHelper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransferServiceApp.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TransferServiceImplIntegrationTest extends TransferServiceTestBase {

	private TransferRequest transferRequest;
	private TransferResponse transferResponse;

	@Autowired
	private ITransferService transferService;

	@Mock
	private IProcessTransaction processTransaction;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Before
	public void setUp() throws TransferServiceException {
		transferRequest = TransferServiceTestHelper.createTransferRequestWithDefaultTransferAmount();
		transferResponse = TransferServiceTestHelper.createTransferResponse();
		Mockito.when(processTransaction.processTransfer(transferRequest.getTransactionModel().getSrcAccountNo(),
				transferRequest.getTransactionModel().getDestAccountNo(),
				transferRequest.getTransactionModel().getAmount())).thenReturn(transferResponse);
	}

	@Test
	public void whenValidTransferHappens_thenVerifyTransferResponse() throws TransferServiceException {
		TransferRequest transferRequest = TransferServiceTestHelper.createTransferRequestWithDefaultTransferAmount();
		TransferResponse actualResponse = transferService.transfer(transferRequest);

		Assert.assertEquals(transferResponse.getSrcAccountModel().getAccountNo(),
				actualResponse.getSrcAccountModel().getAccountNo());
		Assert.assertEquals(transferResponse.getDestAccountModel().getAccountNo(),
				actualResponse.getDestAccountModel().getAccountNo());

	}
}
