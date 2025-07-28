package com.payments.analyzer.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "interest_credit")
public class InterestCredit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestId;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Temporal(TemporalType.DATE)
    private Date interestDate;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

	public Long getInterestId() {
		return interestId;
	}

	public void setInterestId(Long interestId) {
		this.interestId = interestId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getInterestDate() {
		return interestDate;
	}

	public void setInterestDate(Date interestDate) {
		this.interestDate = interestDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
    
    
}

