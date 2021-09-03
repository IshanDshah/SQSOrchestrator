package com.sqs.orchestrator.controller;


import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.*;
import com.sqs.orchestrator.ResponsePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

@RestController
public class MessageReceiverController {

    @Autowired
    private AmazonSQSAsync amazonSQSAsync;

    @Value("${cloud.aws.end-point.uri}")
    private String endPoint;


    @RequestMapping("/receiveMessage")
    public ResponsePayload messageReceiver(@RequestBody String payload,@RequestParam boolean isBatch) throws IOException {

        if(payload!=null && payload.length()!=0){

            ResponsePayload responsePayload1 = messageSender(payload,isBatch);
            //responsePayload=new ResponsePayload(HttpStatus.OK,"Message published successfully");
            return responsePayload1;
        }

        return  new ResponsePayload(HttpStatus.BAD_REQUEST,"Please check the message");
    }


    public ResponsePayload messageSender( String payload, boolean isBatch) throws IOException {
        ResponsePayload responsePayload;
        if(payload!=null && payload.length()!=0){

            //Send to Kafka
            //sendtoSQS
            sendTOSQS(payload,isBatch);
            responsePayload=new ResponsePayload(HttpStatus.OK,"Message published successfully");
            return responsePayload;
        }

        return  new ResponsePayload(HttpStatus.BAD_REQUEST,"Please check the message");
    }

    @RequestMapping("/publishMessage")
    public ResponsePayload messageSender(@RequestBody String payload) throws IOException {
        ResponsePayload responsePayload;
        if(payload!=null && payload.length()!=0){

            //Send to Kafka
            //sendtoSQS
            boolean successStatus= sendTOSQS(payload, Boolean.TRUE);
            if(successStatus)
                responsePayload=new ResponsePayload(HttpStatus.OK,"Message published successfully");
            else
                responsePayload=new ResponsePayload(HttpStatus.EXPECTATION_FAILED,"Some of the messages failed to publish");
            return responsePayload;
        }

        return  new ResponsePayload(HttpStatus.BAD_REQUEST,"Please check the message");
    }

    private boolean sendTOSQS(String payload, boolean isbatch) throws IOException {
        boolean isDone;
        if(isbatch){
            String[] split = payload.split(";");
            SendMessageBatchRequestEntry[] entries=new SendMessageBatchRequestEntry[split.length];
            for (int i=0;i<split.length;i++){
                entries[i]=new SendMessageBatchRequestEntry(UUID.randomUUID().toString(),split[i]);

            }
            SendMessageBatchRequest send_batch_request = new SendMessageBatchRequest()
                    .withQueueUrl(endPoint)
                    .withEntries(entries);
            SendMessageBatchResult sendMessageBatchResult = amazonSQSAsync.sendMessageBatch(send_batch_request);

            isDone=sendMessageBatchResult.getFailed().size()!=0;
        }else {
            Future<SendMessageResult> sendMessageResultFuture = amazonSQSAsync.sendMessageAsync(new SendMessageRequest(endPoint, payload));
             isDone=sendMessageResultFuture.isDone();
        }

            return isDone;
    }

    @GetMapping("/")
    public String root() {
        return "index";
    }





    @GetMapping("/consumerStart")
    @ResponseBody
    public Object loadMessagesFromQueue() {

        //System.out.println("Queue Messages: " + message);
        // short polling
//TOdo Clean up the message
        List<Message> messages = this.amazonSQSAsync.receiveMessage(endPoint).getMessages();
        System.out.println(messages);


        return messages.toString();
    }
    @ResponseBody
    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public Object emptyQueue() {

        // short polling
        PurgeQueueRequest psr=new PurgeQueueRequest(endPoint);

        PurgeQueueResult purgeQueueResult = this.amazonSQSAsync.purgeQueue(psr);
        return purgeQueueResult.getSdkResponseMetadata();
    }

}


