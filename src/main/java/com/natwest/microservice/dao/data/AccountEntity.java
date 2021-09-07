package com.natwest.microservice.dao.data;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountEntity {

	private Long accountNo;
	private BigDecimal balance;

	public AccountEntity(Long accountNo, BigDecimal balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "AccountEntity {" + "accountNo=" + accountNo + ", balance=" + balance + '}';
	}

	public AccountEntity debit(BigDecimal amount) {
		return new AccountEntity(accountNo, balance.subtract(amount));
	}

	public AccountEntity credit(BigDecimal amount) {
		return new AccountEntity(accountNo, balance.add(amount));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AccountEntity that = (AccountEntity) o;
		return accountNo == that.accountNo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNo);
	}
}
