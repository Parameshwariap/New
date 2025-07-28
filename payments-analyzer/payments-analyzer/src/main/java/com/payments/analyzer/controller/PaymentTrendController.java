package com.payments.analyzer.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payments.analyzer.dto.PaymentTrendDTO;
import com.payments.analyzer.service.PaymentTrendService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentTrendController {

	private final PaymentTrendService paymentTrendService;

	public PaymentTrendController(PaymentTrendService paymentTrendService) {
		this.paymentTrendService = paymentTrendService;
	}

	@GetMapping("/trend/{customerId}")
	public ResponseEntity<List<PaymentTrendDTO>> getTrend(@PathVariable Long customerId) {
			List<PaymentTrendDTO> trends = paymentTrendService.getPaymentTrends(customerId);
			return ResponseEntity.ok(trends);
	}

}