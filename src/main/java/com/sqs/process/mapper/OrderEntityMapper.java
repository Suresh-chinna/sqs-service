package com.sqs.process.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.sqs.process.entity.OrderItemEntity;
import com.sqs.process.entity.OrderStatusEntity;
import com.sqs.process.entity.OrdersEntity;
import com.sqs.process.entity.PaymentStatusEntity;
import com.sqs.process.entity.ShipmentStatusEntity;
import com.sqs.process.model.OrderItemModel;
import com.sqs.process.model.OrderStatusModel;
import com.sqs.process.model.OrdersModel;
import com.sqs.process.model.PaymentStatusModel;
import com.sqs.process.model.ShipmentStatusModel;

@Component
public class OrderEntityMapper {
	public OrdersEntity modelToOrderEntity(OrdersModel ordersModel) {
		ordersModel.setOrderId(ordersModel.getOrderId());
		OrdersEntity ordersEntity = new OrdersEntity();
		BeanUtils.copyProperties(ordersModel, ordersEntity);
		ordersEntity.setOrderdate(LocalDate.now());
		ordersEntity.setOrderStatusEntity(modelToOrderStatusEntity(ordersModel.getOrderStatusModel()));
		ordersEntity.setPaymentStatusEntity(modelToPaymentStatusEntity(ordersModel.getPaymentStatusModel()));
		ordersEntity.setShipementStatus(modelToShipmentStatusEntity(ordersModel.getShipementStatusModel()));
		ordersEntity.setOrderItemList(modelToOrderItemEntities(ordersModel.getOrderItemModelList(), ordersEntity));
		return ordersEntity;

	}

	public OrderStatusEntity modelToOrderStatusEntity(OrderStatusModel OrderStatusModel) {
		OrderStatusModel.setOrderStatusId(OrderStatusModel.getOrderStatusId());
		OrderStatusEntity orderStatusEntity = new OrderStatusEntity();
		BeanUtils.copyProperties(OrderStatusModel, orderStatusEntity);
		return orderStatusEntity;

	}

	public PaymentStatusEntity modelToPaymentStatusEntity(PaymentStatusModel paymentStatusModel) {
		paymentStatusModel.setPaymentStatusId(paymentStatusModel.getPaymentStatusId());
		PaymentStatusEntity paymentStatusEntity = new PaymentStatusEntity();
		BeanUtils.copyProperties(paymentStatusModel, paymentStatusEntity);
		return paymentStatusEntity;

	}

	public ShipmentStatusEntity modelToShipmentStatusEntity(ShipmentStatusModel shipmentStatusModel) {
		shipmentStatusModel.setShipmentStatusId(shipmentStatusModel.getShipmentStatusId());
		ShipmentStatusEntity shipmentStatusEntity = new ShipmentStatusEntity();
		BeanUtils.copyProperties(shipmentStatusModel, shipmentStatusEntity);
		return shipmentStatusEntity;

	}

	public OrderItemEntity modelToOrderItemEntity(OrderItemModel orderItemModel, OrdersEntity ordersEntity) {
		orderItemModel.setOrderItemId(orderItemModel.getOrderItemId());
		OrderItemEntity orderItemEntity = new OrderItemEntity();
		BeanUtils.copyProperties(orderItemModel, orderItemEntity);
		orderItemEntity.setOrdersEntity(ordersEntity);
		return orderItemEntity;
	}

	public List<OrderItemEntity> modelToOrderItemEntities(List<OrderItemModel> orderItemModels,
			OrdersEntity ordersEntity) {
		return orderItemModels.stream().map(orderModel -> modelToOrderItemEntity(orderModel, ordersEntity))
				.collect(Collectors.toList());

	}

	public int getId(int id) {
		if (id <= 0) {
			id = UUID.randomUUID().hashCode();
		}
		return id;
	}
}
