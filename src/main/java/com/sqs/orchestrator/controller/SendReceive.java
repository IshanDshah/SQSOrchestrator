package com.sqs.orchestrator.controller;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

@Configuration

public class SendReceive {


//    @Value("${cloud.aws.credentials.access-key}")
//    private static String accessKey;
//
//    @Value("${cloud.aws.credentials.secret-key}")
//    private static String secretKey;
//
//    private static final String QUEUE_NAME = "https://sqs.us-east-2.amazonaws.com/071156060986/PayloadOrchestrator";
//
//    public static void main(String[] args)
//    {
//
//        accessKey="AKIARBEJ3KM5BTEHVOD2";
//
//        secretKey="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";
//        final AmazonSQS sqs= AmazonSQSAsyncClientBuilder
//                .standard()
//                .withRegion(Regions.US_EAST_2)
//                .withCredentials(
//                        new AWSStaticCredentialsProvider(
//                                new BasicAWSCredentials(accessKey,secretKey)
//                        )
//                )
//                .build();
//
//
//
//
//        String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();
//
//        SendMessageRequest send_msg_request = new SendMessageRequest()
//                .withQueueUrl(queueUrl)
//                .withMessageBody("hello world")
//                .withDelaySeconds(5);
//        sqs.sendMessage(send_msg_request);
//
//
//        // Send multiple messages to the queue
//        SendMessageBatchRequest send_batch_request = new SendMessageBatchRequest()
//                .withQueueUrl(queueUrl)
//                .withEntries(
//                        new SendMessageBatchRequestEntry(
//                                "msg_1", "Hello from message 1"),
//                        new SendMessageBatchRequestEntry(
//                                "msg_2", "Hello from message 2")
//                                .withDelaySeconds(10));
//        sqs.sendMessageBatch(send_batch_request);
//
//        // receive messages from the queue
//        List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
//
//        // delete messages from the queue
//        for (Message m : messages) {
//            sqs.deleteMessage(queueUrl, m.getReceiptHandle());
//        }
//    }
}