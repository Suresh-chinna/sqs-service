package com.sqs.process.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders", schema = "order_schema")
public class OrdersEntity {

	@Id
	@Column(name = "order_id")
	private int orderId;

	private String description;

	@Column(name = "order_date")
	private LocalDate orderdate;

	@Column(name = "order_total_amount")
	private double orderTotalAmount;

	@Column(name = "total_items")
	private int totalItems;

	@JsonManagedReference("orders-order_item")
	@OneToMany(mappedBy = "ordersEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderItemEntity> orderItemList;

	@OneToOne
	@JoinColumn(name = "payment_status_id")
	private PaymentStatusEntity paymentStatusEntity;

	@OneToOne
	@JoinColumn(name = "shipment_status_id")
	private ShipmentStatusEntity shipmentStatus;

	@OneToOne
	@JoinColumn(name = "order_status_id")
	private OrderStatusEntity orderStatusEntity;

	public OrdersEntity() {
	}

	public OrdersEntity(int orderId, String description, LocalDate orderdate, double orderTotalAmount, int totalItems,
			List<OrderItemEntity> orderItemList, PaymentStatusEntity paymentStatusEntity,
			ShipmentStatusEntity shipementStatus, OrderStatusEntity orderStatusEntity) {
		super();
		this.orderId = orderId;
		this.description = description;
		this.orderdate = orderdate;
		this.orderTotalAmount = orderTotalAmount;
		this.totalItems = totalItems;
		this.orderItemList = orderItemList;
		this.paymentStatusEntity = paymentStatusEntity;
		this.shipmentStatus = shipementStatus;
		this.orderStatusEntity = orderStatusEntity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(LocalDate orderdate) {
		this.orderdate = orderdate;
	}

	public double getOrderTotalAmount() {
		return orderTotalAmount;
	}

	public void setOrderTotalAmount(double orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public List<OrderItemEntity> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItemEntity> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public PaymentStatusEntity getPaymentStatusEntity() {
		return paymentStatusEntity;
	}

	public void setPaymentStatusEntity(PaymentStatusEntity paymentStatusEntity) {
		this.paymentStatusEntity = paymentStatusEntity;
	}

	public ShipmentStatusEntity getShipementStatus() {
		return shipmentStatus;
	}

	public void setShipementStatus(ShipmentStatusEntity shipementStatus) {
		this.shipmentStatus = shipementStatus;
	}

	public OrderStatusEntity getOrderStatusEntity() {
		return orderStatusEntity;
	}

	public void setOrderStatusEntity(OrderStatusEntity orderStatusEntity) {
		this.orderStatusEntity = orderStatusEntity;
	}

}
