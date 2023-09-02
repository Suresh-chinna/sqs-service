	package com.sqs.process.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "shipment_status", schema = "order_schema")
public class ShipmentStatusEntity {

	@Id
	@Column(name = "shipment_status_id")
	private int shipmentStatusId;

	@Column(name = "shipment_status")
	private String shipmentStatus;

	public ShipmentStatusEntity() {
		super();
	}

	public ShipmentStatusEntity(int shipmentStatusId, String shipmentStatus) {
		super();
		this.shipmentStatusId = shipmentStatusId;
		this.shipmentStatus = shipmentStatus;
	}

	public int getShipmentStatusId() {
		return shipmentStatusId;
	}

	public void setShipmentStatusId(int shipmentStatusId) {
		this.shipmentStatusId = shipmentStatusId;
	}

	public String getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

}
