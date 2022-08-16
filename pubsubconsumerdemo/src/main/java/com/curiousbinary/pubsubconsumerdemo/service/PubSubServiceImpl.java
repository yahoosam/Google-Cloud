package com.curiousbinary.pubsubconsumerdemo.service;

import com.curiousbinary.pubsubconsumerdemo.pubsub.GMessageSubscriber;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class PubSubServiceImpl implements PubSubService {

    @Autowired
    private GMessageSubscriber gMessageSubscriber;
    private static final Logger log = LoggerFactory.getLogger(PubSubServiceImpl.class);

    @Override
    public void getGMessages() {
        log.info("Inside getGMessages");

        String projectId = "pubsubdemo-359610";
        String subscriptionId = "demotopic-sub";

        ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);

        Subscriber subscriber = null;
        try {
            subscriber = Subscriber.newBuilder(subscriptionName, gMessageSubscriber).build();

            // Start the subscriber.
            subscriber.startAsync().awaitRunning();

            log.info("Listening for messages on: " + subscriptionName.toString());
            // Allow the subscriber to run for 30s unless an unrecoverable error occurs.
            subscriber.awaitTerminated(30, TimeUnit.SECONDS);

            // Continuously check for messages
            //subscriber.awaitTerminated();
        } catch (TimeoutException timeoutException) {
            // Shut down the subscriber after 30s. Stop receiving messages.
            log.info("Shutting down the subscriber after 30s...");
            subscriber.stopAsync();
        }

    }
}
