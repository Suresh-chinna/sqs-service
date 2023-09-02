package com.sqs.process;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

@SpringBootApplication
@EnableScheduling
public class SqsServiceApplication {

	@Value("${app.config.aws.access-key-id}")
	private String awsAccessKeyId;

	@Value("${app.config.aws.secret-key-id}")
	private String awsSecretKeyId;
	
	@Value("${app.config.aws.region}")
	private String region;
	
	public static void main(String[] args) {
		SpringApplication.run(SqsServiceApplication.class, args);
	}	

	@Bean
	public AmazonSQS amazonSQSClient() {
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKeyId, awsSecretKeyId);
		return AmazonSQSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(region).build();
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
