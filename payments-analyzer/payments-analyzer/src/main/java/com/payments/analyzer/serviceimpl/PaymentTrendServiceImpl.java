package com.payments.analyzer.serviceimpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payments.analyzer.dto.PaymentTrendDTO;
import com.payments.analyzer.entity.Payment;
import com.payments.analyzer.repository.PaymentRepository;
import com.payments.analyzer.service.PaymentTrendService;

@Service
@Transactional
public class PaymentTrendServiceImpl implements PaymentTrendService {

	private final PaymentRepository paymentRepository;

	public PaymentTrendServiceImpl(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@Override
	public List<PaymentTrendDTO> getPaymentTrends(Long customerId) {
		LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
		Date fromDate = Date.from(threeMonthsAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());

		List<Payment> payments = paymentRepository.findRecentPayments(fromDate);

		List<Payment> relevantPayments = payments.stream()
				.filter(p -> Objects.equals(p.getSenderAccount().getCustomer().getCustomerId(), customerId)
						|| Objects.equals(p.getReceiverAccountNumber().getCustomer().getCustomerId(), customerId))
				.toList();

		if (relevantPayments.isEmpty()) {
			throw new RuntimeException("No payment data found for the last 3 months.");
		}

		Map<String, BigDecimal> madeMap = new HashMap<>();
		Map<String, BigDecimal> receivedMap = new HashMap<>();

		for (Payment p : relevantPayments) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(p.getScheduledDate());
			String key = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH).toUpperCase() + " "
					+ cal.get(Calendar.YEAR);

			if (Objects.equals(p.getSenderAccount().getCustomer().getCustomerId(), customerId)) {
				madeMap.merge(key, p.getAmount(), BigDecimal::add);
			}

			if (Objects.equals(p.getReceiverAccountNumber().getCustomer().getCustomerId(), customerId)) {
				receivedMap.merge(key, p.getAmount(), BigDecimal::add);
			}
		}
		Set<String> months = new TreeSet<>(Comparator.reverseOrder());
		months.addAll(madeMap.keySet());
		months.addAll(receivedMap.keySet());
		List<PaymentTrendDTO> trends=new ArrayList<>();
		for(String month:months.stream().limit(3).toList()) {
			trends.add(new PaymentTrendDTO(month,
			madeMap.getOrDefault(month, BigDecimal.ZERO),receivedMap.getOrDefault(month, BigDecimal.ZERO)
			));
		}
		return trends;

	}
}