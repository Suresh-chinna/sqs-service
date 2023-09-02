package com.sqs.process.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqs.process.model.OrdersModel;
import com.sqs.process.service.OrderSqsService;

@Service
public class OrderSqsServiceImpl implements OrderSqsService {

	@Value("${app.config.message.queue.topic}")
	private String messageQueueTopic;

	@Autowired
	private AmazonSQS amazonSQSClient;
	
	@Override
	public void orderProcess(OrdersModel ordersModel) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String jsonStr = objectMapper.writeValueAsString(ordersModel);
			GetQueueUrlResult queueUrl = amazonSQSClient.getQueueUrl(messageQueueTopic);
			amazonSQSClient.sendMessage(queueUrl.getQueueUrl(), jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
