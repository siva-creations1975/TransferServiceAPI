package com.natwest.microservice.dao.data;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.natwest.microservice.service.TransferServiceConstants;

@Component
public class DataPopulator {

	private final IAccountDao accountDao;

	@Autowired
	public DataPopulator(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@PostConstruct
	public void insertAccountData() {
		accountDao.save(new AccountEntity(TransferServiceConstants.DEFAULT_SOURCE_ACCOUNT_NUMBER,
				TransferServiceConstants.DEFAULT_SOURCE_ACCOUNT_BALANCE));
		accountDao.save(new AccountEntity(TransferServiceConstants.DEFAULT_DESTINATION_ACCOUNT_NUMBER,
				TransferServiceConstants.DEFAULT_DESTINATION_ACCOUNT_BALANCE));
	}
}
