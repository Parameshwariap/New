package com.payments.analyzer.controller;

import com.payments.analyzer.dto.PaymentTrendDTO;
import com.payments.analyzer.service.PaymentTrendService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentTrendControllerTest {

    private PaymentTrendService paymentTrendService;
    private PaymentTrendController paymentTrendController;

    @BeforeEach
    void setUp() {
        paymentTrendService = mock(PaymentTrendService.class);
        paymentTrendController = new PaymentTrendController(paymentTrendService);
    }

    @Test
    void testGetTrend_ReturnsPaymentTrends() {
        Long customerId = 1L;

        List<PaymentTrendDTO> mockTrends = Arrays.asList(
                new PaymentTrendDTO("2025-06", new BigDecimal("1000.00"), new BigDecimal("1200.00")),
                new PaymentTrendDTO("2025-07", new BigDecimal("1100.00"), new BigDecimal("1300.00"))
        );

        when(paymentTrendService.getPaymentTrends(customerId)).thenReturn(mockTrends);

        ResponseEntity<List<PaymentTrendDTO>> response = paymentTrendController.getTrend(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());

        PaymentTrendDTO first = response.getBody().get(0);
        assertEquals("2025-06", first.getMonthYear());
        assertEquals(new BigDecimal("1000.00"), first.getPaymentsMade());
        assertEquals(new BigDecimal("1200.00"), first.getPaymentsReceived());
    }

    @Test
    void testGetTrend_EmptyResult() {
        Long customerId = 2L;

        when(paymentTrendService.getPaymentTrends(customerId)).thenReturn(List.of());

        ResponseEntity<List<PaymentTrendDTO>> response = paymentTrendController.getTrend(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }
}
