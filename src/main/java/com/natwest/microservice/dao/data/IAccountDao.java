package com.natwest.microservice.dao.data;

import java.util.Map;

/**
 * A repository in charge of Account data.
 */
public interface IAccountDao {

	/**
	 * @return A {@code Map} of all the accounts currently stored in this repository.
	 */
	Map<Long, AccountEntity> getAll();

	/**
	 * Clears all the accounts currently stored in this repository.
	 */
	void clear();

	/**
	 * Saves the given {@link AccountEntity} to the repository. If an account entity with the same Id already exists, it will be overridden.
	 * @param accountEntity The {@code AccountEntity} to save to the repository.
	 */
	void save(AccountEntity accountEntity);
}
