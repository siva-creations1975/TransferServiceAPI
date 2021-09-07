package com.natwest.microservice.dao.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

/**
 * A simple in memory implementation of the {@link IAccountDao}.
 */
@Component
public class AccountDaoImpl implements IAccountDao {

	private static Map<Long, AccountEntity> accounts = new ConcurrentHashMap<>();

	@Override
	public Map<Long, AccountEntity> getAll() {
		return accounts;
	}

	@Override
	public void clear() {
		accounts.clear();
	}

	@Override
	public void save(AccountEntity accountEntity) {
		accounts.put(accountEntity.getAccountNo(), accountEntity);
	}
}
