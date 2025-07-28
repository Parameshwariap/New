package com.payments.analyzer.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.payments.analyzer.entity.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
	@Query("SELECT p FROM Payment p WHERE p.scheduledDate >= :fromDate")
	List<Payment> findRecentPayments(Date fromDate);
}
