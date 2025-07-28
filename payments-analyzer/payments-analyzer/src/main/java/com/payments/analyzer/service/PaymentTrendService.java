package com.payments.analyzer.service;

import java.util.List;

import com.payments.analyzer.dto.PaymentTrendDTO;

public interface PaymentTrendService {
	List<PaymentTrendDTO> getPaymentTrends(Long customerId);
}
