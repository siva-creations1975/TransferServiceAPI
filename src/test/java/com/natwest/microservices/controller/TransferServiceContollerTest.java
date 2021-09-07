package com.natwest.microservices.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

//import com.blog.samples.model.Account;
//import com.blog.samples.model.EnumAccountType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.natwest.microservice.TransferServiceApp;
import com.natwest.microservice.dao.data.IAccountDao;
import com.natwest.microservice.dao.data.AccountEntity;
import com.natwest.microservice.model.AccountModel;
import com.natwest.microservice.model.TransactionModel;
import com.natwest.microservice.model.TransferRequest;
import com.natwest.microservice.model.TransferResponse;
import com.natwest.microservice.service.ITransferService;
import com.natwest.microservice.transaction.IProcessTransaction;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = { TransferServiceApp.class })
public class TransferServiceContollerTest {

	private Long srcAccountNo;
	private Long destAccountNo;
	private BigDecimal amount;
	private AccountModel srcAccountModel;
	private AccountModel destAccountModel;
	private TransferResponse expectedResponse;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private ITransferService iTransferService;

	@MockBean
	private IAccountDao accountDao;

	@MockBean
	private IProcessTransaction processTransaction;

	@Before
	public void setUp() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		srcAccountNo = 1001L;
		destAccountNo = 1002L;
		amount = new BigDecimal(100);
		srcAccountModel = new AccountModel(srcAccountNo, new BigDecimal(1000));
		destAccountModel = new AccountModel(destAccountNo, new BigDecimal(1000));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void should_CreateAccount_When_ValidRequest() throws Exception {

		TransferRequest transferRequest = new TransferRequest();
		TransactionModel transactionModel = new TransactionModel(srcAccountNo, destAccountNo, amount);
		transferRequest.setTransactionModel(transactionModel);

		srcAccountModel = new AccountModel(srcAccountNo, new BigDecimal(1000));
		destAccountModel = new AccountModel(destAccountNo, new BigDecimal(1000));
		expectedResponse = new TransferResponse(srcAccountModel, destAccountModel, "");

		when(iTransferService.transfer(transferRequest)).thenReturn(expectedResponse);

		Map<Long, AccountEntity> accounts = new ConcurrentHashMap<>();

		AccountEntity accountEntity1 = new AccountEntity(1001L, new BigDecimal(1000));
		AccountEntity accountEntity2 = new AccountEntity(1002L, new BigDecimal(1000));

		accounts.put(accountEntity1.getAccountNo(), accountEntity1);
		accounts.put(accountEntity2.getAccountNo(), accountEntity2);

		when(accountDao.getAll()).thenReturn(accounts);

		when(processTransaction.processTransfer(srcAccountNo, destAccountNo, amount)).thenReturn(expectedResponse);

		mockMvc.perform(post("/ms/app/v1/transfer-service").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(transferRequest))).andExpect(status().isOk());

	}
}