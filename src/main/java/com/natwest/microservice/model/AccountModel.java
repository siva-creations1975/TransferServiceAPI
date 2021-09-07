package com.natwest.microservice.model;

import java.math.BigDecimal;

public class AccountModel {

	private Long accountNo;
	private BigDecimal balance;
	public AccountModel() {
	}
	public AccountModel(Long accountNo, BigDecimal balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "TransactionModel {" + "accountNo=" + accountNo + ", balance=" + balance + '}';
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
}