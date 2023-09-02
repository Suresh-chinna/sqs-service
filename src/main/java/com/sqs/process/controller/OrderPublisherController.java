package com.sqs.process.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sqs.process.model.CreateExpenseDto;
import com.sqs.process.model.OrdersModel;
import com.sqs.process.service.OrderSqsService;
import com.sqs.process.serviceimpl.OrderPublisherService;

@RestController
@RequestMapping(value = "/api/v1/expenses")
public class OrderPublisherController {
	@Autowired
	OrderSqsService orderSqsService;
	
	@Autowired
	OrderPublisherService orderPublisherService;
	
	@PostMapping
	public void createExpense(@RequestBody CreateExpenseDto createExpenseDto) throws JsonProcessingException {
		orderPublisherService.publishExpense(createExpenseDto);
	}
	@PostMapping("/orderProcess")
	public void orderProcess(@RequestBody OrdersModel ordersModel) {
		orderSqsService.orderProcess(ordersModel);
	}
}
