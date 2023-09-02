package com.sqs.process.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_status", schema = "order_schema")
public class OrderStatusEntity {

	@Id
	@Column(name = "order_status_id")
	private int orderStatusId;

	@Column(name = "order_status")
	private String orderStatus;

	public OrderStatusEntity() {
	}

	public OrderStatusEntity(int orderStatusId, String orderStatus) {
		super();
		this.orderStatusId = orderStatusId;
		this.orderStatus = orderStatus;
	}

	public int getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(int orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}
