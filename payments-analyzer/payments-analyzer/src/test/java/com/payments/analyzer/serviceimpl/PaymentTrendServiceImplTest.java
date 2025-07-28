package com.payments.analyzer.serviceimpl;

import com.payments.analyzer.dto.PaymentTrendDTO;
import com.payments.analyzer.entity.Account;
import com.payments.analyzer.entity.Customer;
import com.payments.analyzer.entity.Payment;
import com.payments.analyzer.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentTrendServiceImplTest {

    private PaymentRepository paymentRepository;
    private PaymentTrendServiceImpl paymentTrendService;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);
        paymentTrendService = new PaymentTrendServiceImpl(paymentRepository);
    }

    @Test
    void testGetPaymentTrends_ReturnsTrends() {
        Long customerId = 1L;

        Customer senderCustomer = new Customer();
        senderCustomer.setCustomerId(customerId);

        Customer receiverCustomer = new Customer();
        receiverCustomer.setCustomerId(2L);

        Account senderAccount = new Account();
        senderAccount.setCustomer(senderCustomer);

        Account receiverAccount = new Account();
        receiverAccount.setCustomer(receiverCustomer);

        Payment payment1 = new Payment();
        payment1.setSenderAccount(senderAccount);
        payment1.setReceiverAccountNumber(receiverAccount);
        payment1.setAmount(new BigDecimal("1000.00"));
        payment1.setScheduledDate(new Date());

        Payment payment2 = new Payment();
        payment2.setSenderAccount(receiverAccount);
        payment2.setReceiverAccountNumber(senderAccount);
        payment2.setAmount(new BigDecimal("500.00"));
        payment2.setScheduledDate(new Date());

        List<Payment> payments = Arrays.asList(payment1, payment2);

        Date fromDate = Date.from(LocalDate.now().minusMonths(3).atStartOfDay(ZoneId.systemDefault()).toInstant());
        when(paymentRepository.findRecentPayments(fromDate)).thenReturn(payments);

        List<PaymentTrendDTO> trends = paymentTrendService.getPaymentTrends(customerId);

        assertNotNull(trends);
        assertFalse(trends.isEmpty());
        assertEquals(1, trends.size());

        PaymentTrendDTO trend = trends.get(0);
        assertEquals(new BigDecimal("1000.00"), trend.getPaymentsMade());
        assertEquals(new BigDecimal("500.00"), trend.getPaymentsReceived());
    }

    @Test
    void testGetPaymentTrends_NoRelevantPayments_ThrowsException() {
        Long customerId = 1L;

        Date fromDate = Date.from(LocalDate.now().minusMonths(3).atStartOfDay(ZoneId.systemDefault()).toInstant());
        when(paymentRepository.findRecentPayments(fromDate)).thenReturn(Collections.emptyList());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            paymentTrendService.getPaymentTrends(customerId);
        });

        assertEquals("No payment data found for the last 3 months.", exception.getMessage());
    }
}
