package com.payments.analyzer.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "sender_account_id", nullable = false)
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_account_number", nullable = false)
    private Account receiverAccountNumber;

    @Column(precision = 15, scale = 2)
    private BigDecimal amount;

    @Lob
    private String remarks;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Temporal(TemporalType.DATE)
    private Date scheduledDate;

    @Enumerated(EnumType.STRING)
    private RecurrenceFrequency recurrenceFrequency;

    private Integer recurrenceCount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private Timestamp createdAt;

    public enum PaymentMode {
        CHEQUE, INTERNET_BANKING, ECS
    }

    public enum RecurrenceFrequency {
        DAILY, MONTHLY
    }

    public enum PaymentStatus {
        PENDING, COMPLETED, FAILED, CANCELLED
    }

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Account getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(Account senderAccount) {
		this.senderAccount = senderAccount;
	}
	

	public Account getReceiverAccountNumber() {
		return receiverAccountNumber;
	}

	public void setReceiverAccountNumber(Account receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public RecurrenceFrequency getRecurrenceFrequency() {
		return recurrenceFrequency;
	}

	public void setRecurrenceFrequency(RecurrenceFrequency recurrenceFrequency) {
		this.recurrenceFrequency = recurrenceFrequency;
	}

	public Integer getRecurrenceCount() {
		return recurrenceCount;
	}

	public void setRecurrenceCount(Integer recurrenceCount) {
		this.recurrenceCount = recurrenceCount;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
    
    
}

