package com.sqs.orchestrator;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.io.BufferedReader;
import java.io.IOException;



@SpringBootApplication
public class OrchestratorApplication {

	public static void main(String[] args) throws IOException, ParseException {


//
//		int apiData = getApiData(args[0]);
//		postApiData(apiData);
		SpringApplication.run(OrchestratorApplication.class, args);
	}

	@Value("${cloud.aws.credentials.access-key}")
	private String accessKey;

	@Value("${cloud.aws.credentials.secret-key}")
	private String secretKey;



	@Bean
	@Primary
	 AmazonSQSAsync buildAmazonSQSAsync() {

		return AmazonSQSAsyncClientBuilder
				.standard()
				.withRegion(Regions.US_EAST_1)
				.withCredentials(
						new AWSStaticCredentialsProvider(
								new BasicAWSCredentials(accessKey,secretKey)
						)
				)
				.build();

	}


}
