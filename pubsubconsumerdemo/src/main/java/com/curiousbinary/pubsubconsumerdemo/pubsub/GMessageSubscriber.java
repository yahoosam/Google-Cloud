package com.curiousbinary.pubsubconsumerdemo.pubsub;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GMessageSubscriber implements MessageReceiver {
    private static final Logger log = LoggerFactory.getLogger(GMessageSubscriber.class);

    @Override
    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {
        log.info("Inside receiveMessage");
        log.info(pubsubMessage.getData().toStringUtf8());
        // Handle the message as required with the business logic and acknowledge
        log.info("Acknowledge the message...");
        ackReplyConsumer.ack();
    }
}
