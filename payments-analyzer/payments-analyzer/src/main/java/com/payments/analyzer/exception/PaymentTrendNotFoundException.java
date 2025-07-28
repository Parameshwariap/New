package com.payments.analyzer.exception;

public class PaymentTrendNotFoundException extends RuntimeException {
    public PaymentTrendNotFoundException(String message) {
        super(message);
    }
}