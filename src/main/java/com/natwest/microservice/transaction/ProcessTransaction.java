package com.natwest.microservice.transaction;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.natwest.microservice.dao.data.IAccountDao;
import com.natwest.microservice.dao.data.AccountEntity;
import com.natwest.microservice.model.AccountModel;
import com.natwest.microservice.model.TransferResponse;
import com.natwest.microservice.service.TransferServiceConstants;
import com.natwest.microservice.service.TransferServiceException;

/**
 * A class which is responsible to process the transfer request transactions.
 */
@Component
public class ProcessTransaction implements IProcessTransaction {

	@Autowired
	private IAccountDao accountDao;

	private final Object lock = new Object();

	/**
	 * A method which is responsible to process the transfer transaction request and
	 * sends the amount from source account to destination account
	 *
	 * @param srcAccountNo  source account number
	 * @param destAccountNo destination account number
	 * @param amount        amount
	 * @return TransferResponse
	 * @exception TransferServiceException
	 */
	@Override
	public TransferResponse processTransfer(Long scrAccountId, Long destAccountId, BigDecimal amount)
			throws TransferServiceException {

		Map<Long, AccountEntity> accounts = accountDao.getAll();

		if (Objects.equals(scrAccountId, destAccountId)) {
			throw new TransferServiceException(TransferServiceConstants.DESTINATION_ACCOUNT_SHOULD_BE_DIFFERENT);
		}

		if (!accounts.containsKey(scrAccountId)) {
			throw new TransferServiceException(TransferServiceConstants.SOURCE_ACCOUNT_IS_NOT_FOUND);
		}

		if (!accounts.containsKey(destAccountId)) {
			throw new TransferServiceException(TransferServiceConstants.DESTINATION_ACCOUNT_IS_NOT_FOUND);
		}

		synchronized (lock) {
			AccountEntity srcAccount = accounts.get(scrAccountId);
			AccountEntity destAccount = accounts.get(destAccountId);

			if (srcAccount.getBalance().compareTo(amount) < 0) {
				throw new TransferServiceException(TransferServiceConstants.NO_SUFFICIENT_FUNDS_IN_THE_ACCOUNT);
			}

			accounts.replace(scrAccountId, srcAccount.debit(amount));
			accounts.replace(destAccountId, destAccount.credit(amount));

			AccountModel sourceAccountModel = new AccountModel(accounts.get(scrAccountId).getAccountNo(),
					accounts.get(scrAccountId).getBalance());
			AccountModel destAccountModel = new AccountModel(accounts.get(destAccountId).getAccountNo(),
					accounts.get(destAccountId).getBalance());

			return new TransferResponse(sourceAccountModel, destAccountModel, null);
		}
	}
}
