package com.sqs.process.service;

import com.sqs.process.model.OrdersModel;

public interface OrderSqsService {
	public void orderProcess(OrdersModel ordersModel);
}
