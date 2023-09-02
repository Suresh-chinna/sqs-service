package com.sqs.process.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_status", schema = "order_schema")
public class PaymentStatusEntity {

	@Id
	@Column(name = "payment_status_id")
	private int paymentStatusId;

	@Column(name = "payment_status")
	private String paymentStatus;

	public PaymentStatusEntity() {
		super();
	}

	public PaymentStatusEntity(int paymentStatusId, String paymentStatus) {
		super();
		this.paymentStatusId = paymentStatusId;
		this.paymentStatus = paymentStatus;
	}

	public int getPaymentStatusId() {
		return paymentStatusId;
	}

	public void setPaymentStatusId(int paymentStatusId) {
		this.paymentStatusId = paymentStatusId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

}
