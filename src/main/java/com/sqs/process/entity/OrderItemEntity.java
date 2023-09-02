package com.sqs.process.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_item", schema = "order_schema")
public class OrderItemEntity {

	@Id
	@Column(name = "order_item_id")
	private int OrderItemId;

	private double price;

	private int quantity;

	@Column(name = "sub_total_amount")
	private double subTotalAmount;

	@JsonBackReference("orders_entity-order_item")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private OrdersEntity ordersEntity;

	public OrderItemEntity() {
		super();
	}

	public OrderItemEntity(int orderItemId, double price, int quantity, double subTotalAmount,
			OrdersEntity ordersEntity) {
		super();
		OrderItemId = orderItemId;
		this.price = price;
		this.quantity = quantity;
		this.subTotalAmount = subTotalAmount;
		this.ordersEntity = ordersEntity;
	}

	public int getOrderItemId() {
		return OrderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		OrderItemId = orderItemId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubTotalAmount() {
		return subTotalAmount;
	}

	public void setSubTotalAmount(double subTotalAmount) {
		this.subTotalAmount = subTotalAmount;
	}

	public OrdersEntity getOrdersEntity() {
		return ordersEntity;
	}

	public void setOrdersEntity(OrdersEntity ordersEntity) {
		this.ordersEntity = ordersEntity;
	}

}
