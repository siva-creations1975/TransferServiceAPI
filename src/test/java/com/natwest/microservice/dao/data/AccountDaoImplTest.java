package com.natwest.microservice.dao.data;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.natwest.microservice.service.TransferServiceConstants;

public class AccountDaoImplTest {

	@InjectMocks
	AccountDaoImpl accountDaoImpl;

	@Mock
	IAccountDao accountDao;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllAccountTest() {
		Map<Long, AccountEntity> accounts = new ConcurrentHashMap<>();

		AccountEntity accountEntity1 = new AccountEntity(TransferServiceConstants.DEFAULT_SOURCE_ACCOUNT_NUMBER,
				TransferServiceConstants.DEFAULT_SOURCE_ACCOUNT_BALANCE);
		AccountEntity accountEntity2 = new AccountEntity(TransferServiceConstants.DEFAULT_DESTINATION_ACCOUNT_NUMBER,
				TransferServiceConstants.DEFAULT_DESTINATION_ACCOUNT_BALANCE);

		accounts.put(accountEntity1.getAccountNo(), accountEntity1);
		accounts.put(accountEntity2.getAccountNo(), accountEntity2);

		when(accountDao.getAll()).thenReturn(accounts);

		Map<Long, AccountEntity> expectedAccounts = accountDao.getAll();

		assertEquals(2, expectedAccounts.size());
	}
}