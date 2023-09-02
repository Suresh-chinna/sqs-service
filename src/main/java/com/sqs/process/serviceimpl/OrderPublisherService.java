package com.sqs.process.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.InvalidMessageContentsException;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqs.process.model.CreateExpenseDto;

@Service
public class OrderPublisherService {
	@Value("${app.config.message.queue.topic}")
	private String messageQueueTopic;

	@Autowired
	private AmazonSQS amazonSQSClient;

	public void publishExpense(CreateExpenseDto createExpenseDto) throws JsonProcessingException {
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = Obj.writeValueAsString(createExpenseDto);
		try {
			GetQueueUrlResult queueUrl = amazonSQSClient.getQueueUrl(messageQueueTopic);
			amazonSQSClient.sendMessage(queueUrl.getQueueUrl(), jsonStr);
		} catch (QueueDoesNotExistException | InvalidMessageContentsException e) {
			System.out.println("Queue does not exist {}" + e.getMessage());
		}
	}
}
