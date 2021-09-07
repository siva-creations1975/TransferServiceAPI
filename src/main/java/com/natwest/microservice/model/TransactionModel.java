package com.natwest.microservice.model;

import java.math.BigDecimal;

public class TransactionModel {

	private Long srcAccountNo;
	private Long destAccountNo;
	private BigDecimal amount;

	public TransactionModel(Long srcAccountNo, Long destAccountNo, BigDecimal amount) {
		this.srcAccountNo = srcAccountNo;
		this.destAccountNo = destAccountNo;
		this.amount = amount;
	}

	public Long getSrcAccountNo() {
		return srcAccountNo;
	}

	public void setSrcAccountNo(Long srcAccountNo) {
		this.srcAccountNo = srcAccountNo;
	}

	public Long getDestAccountNo() {
		return destAccountNo;
	}

	public void setDestAccountNo(Long destAccountNo) {
		this.destAccountNo = destAccountNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "TransactionModel {" + "srcAccountNo=" + srcAccountNo + ", destAccountNo=" + destAccountNo + ", amount="
				+ amount + '}';
	}
}