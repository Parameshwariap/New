package com.payments.analyzer.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class PaymentDTO {
    private Long paymentId;
    private Long senderAccountId;
    private String receiverAccountNumber;
    private BigDecimal amount;
    private String remarks;
    private String paymentMode; // CHEQUE, INTERNET_BANKING, ECS, etc.
    private Date scheduledDate;
    private String recurrenceFrequency; // DAILY, MONTHLY
    private Integer recurrenceCount;
    private String status;
    private Timestamp createdAt;
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public Long getSenderAccountId() {
		return senderAccountId;
	}
	public void setSenderAccountId(Long senderAccountId) {
		this.senderAccountId = senderAccountId;
	}
	public String getReceiverAccountNumber() {
		return receiverAccountNumber;
	}
	public void setReceiverAccountNumber(String receiverAccountNumber) {
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
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public Date getScheduledDate() {
		return scheduledDate;
	}
	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	public String getRecurrenceFrequency() {
		return recurrenceFrequency;
	}
	public void setRecurrenceFrequency(String recurrenceFrequency) {
		this.recurrenceFrequency = recurrenceFrequency;
	}
	public Integer getRecurrenceCount() {
		return recurrenceCount;
	}
	public void setRecurrenceCount(Integer recurrenceCount) {
		this.recurrenceCount = recurrenceCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
    
    
}

