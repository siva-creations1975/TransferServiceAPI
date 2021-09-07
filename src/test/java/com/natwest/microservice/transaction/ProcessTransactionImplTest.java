package com.natwest.microservice.transaction;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.natwest.microservice.dao.data.IAccountDao;
import com.natwest.microservice.dao.data.AccountEntity;
import com.natwest.microservice.model.TransferRequest;
import com.natwest.microservice.model.TransferResponse;
import com.natwest.microservice.service.TransferServiceException;
import com.natwest.microservices.test.base.TransferServiceTestHelper;

public class ProcessTransactionImplTest {

	private TransferRequest transferRequest;
	private TransferResponse transferResponse;
	@Mock
	IProcessTransaction iProcessTransaction;

	@Mock
	IAccountDao accountDao;

	@Before
	public void init() throws TransferServiceException {
		MockitoAnnotations.initMocks(this);
		transferRequest = TransferServiceTestHelper.createTransferRequestWithDefaultTransferAmount();
		transferResponse = TransferServiceTestHelper.createTransferResponse();

		Mockito.when(iProcessTransaction.processTransfer(transferRequest.getTransactionModel().getSrcAccountNo(),
				transferRequest.getTransactionModel().getDestAccountNo(),
				transferRequest.getTransactionModel().getAmount())).thenReturn(transferResponse);
	}

	@Test
	public void processTransactionTest() throws TransferServiceException {
		Map<Long, AccountEntity> accounts = new ConcurrentHashMap<>();

		AccountEntity accountEntity1 = new AccountEntity(1001L, new BigDecimal(1000));
		AccountEntity accountEntity2 = new AccountEntity(1002L, new BigDecimal(1000));

		accounts.put(accountEntity1.getAccountNo(), accountEntity1);
		accounts.put(accountEntity2.getAccountNo(), accountEntity2);

		when(accountDao.getAll()).thenReturn(accounts);

		TransferResponse actualResponse = iProcessTransaction.processTransfer(
				transferRequest.getTransactionModel().getSrcAccountNo(),
				transferRequest.getTransactionModel().getDestAccountNo(),
				transferRequest.getTransactionModel().getAmount());
		Assert.assertEquals(transferResponse.getSrcAccountModel().getAccountNo(),
				actualResponse.getSrcAccountModel().getAccountNo());
		Assert.assertEquals(transferResponse.getDestAccountModel().getAccountNo(),
				actualResponse.getDestAccountModel().getAccountNo());
	}
}