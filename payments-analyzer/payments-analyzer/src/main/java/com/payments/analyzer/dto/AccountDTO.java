package com.payments.analyzer.dto;

import java.math.BigDecimal;

public class AccountDTO {
    private Long accountId;
    private Long customerId;
    private BigDecimal balance;
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
    
    
}

