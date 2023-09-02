package com.sqs.process.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqs.process.mapper.OrderEntityMapper;
import com.sqs.process.model.OrdersModel;
import com.sqs.process.repository.OrderRepository;

@Service
public class OrderConsumerService {
	@Value("${app.config.message.queue.topic}")
	private String messageQueueTopic;

	@Autowired
	private AmazonSQS amazonSQSClient;
	
	@Autowired	
	private OrderRepository orderRepository;

	private OrderEntityMapper orderEntityMapper;

	@Autowired
	public OrderConsumerService(AmazonSQS amazonSQSClient, OrderRepository orderRepository,
			OrderEntityMapper orderEntityMapper) {
		this.amazonSQSClient = amazonSQSClient;
		this.orderRepository = orderRepository;
		this.orderEntityMapper = orderEntityMapper;
	}

	@Scheduled(fixedDelay = 5000) // executes on every 5 second gap.
	public void receiveMessages() {
		try {
			String queueUrl = amazonSQSClient.getQueueUrl(messageQueueTopic).getQueueUrl();
			System.out.println("Reading SQS Queue done: URL {}" + queueUrl);

			ReceiveMessageResult receiveMessageResult = amazonSQSClient.receiveMessage(queueUrl);

			if (!receiveMessageResult.getMessages().isEmpty()) {
				Message message = receiveMessageResult.getMessages().get(0);
				System.out.println("Incoming Message From SQS {}" + message.getMessageId());
				System.out.println("Message Body {}" + message.getBody());
				// processOrders(message.getBody());
				amazonSQSClient.deleteMessage(queueUrl, message.getReceiptHandle());
			}

		} catch (QueueDoesNotExistException e) {
			System.out.println("Queue does not exist {}" + e.getMessage());
		}
	}

	@Scheduled(fixedDelay = 5000) // executes on every 5 second gap.
	public void receiveOrders() {
		try {
			String queueUrl = amazonSQSClient.getQueueUrl(messageQueueTopic).getQueueUrl();
			System.out.println("Reading SQS Queue done: URL {}" + queueUrl);

			ReceiveMessageResult receiveMessageResult = amazonSQSClient.receiveMessage(queueUrl);

			if (!receiveMessageResult.getMessages().isEmpty()) {
				receiveMessageResult.getMessages().stream().forEach(message -> {
					processOrders(message.getBody());
					amazonSQSClient.deleteMessage(queueUrl, message.getReceiptHandle());
				});
			}

		} catch (QueueDoesNotExistException e) {
			System.out.println("Queue does not exist {}" + e.getMessage());
		}
	}

	public void processOrders(String body) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			OrdersModel ordersModel = objectMapper.readValue(body, OrdersModel.class);
			orderRepository.save(orderEntityMapper.modelToOrderEntity(ordersModel));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
