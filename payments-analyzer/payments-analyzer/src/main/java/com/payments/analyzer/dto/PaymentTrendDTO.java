package com.payments.analyzer.dto;

import java.math.BigDecimal;

public class PaymentTrendDTO {
	private String monthYear;
	private BigDecimal paymentsMade;
	private BigDecimal paymentsReceived;

	public PaymentTrendDTO() {
	}

	public PaymentTrendDTO(String monthYear, BigDecimal paymentsMade, BigDecimal paymentsReceived) {
		this.monthYear = monthYear;
		this.paymentsMade = paymentsMade;
		this.paymentsReceived = paymentsReceived;
	}

	public String getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}

	public BigDecimal getPaymentsMade() {
		return paymentsMade;
	}

	public void setPaymentsMade(BigDecimal paymentsMade) {
		this.paymentsMade = paymentsMade;
	}

	public BigDecimal getPaymentsReceived() {
		return paymentsReceived;
	}

	public void setPaymentsReceived(BigDecimal paymentsReceived) {
		this.paymentsReceived = paymentsReceived;
	}

}