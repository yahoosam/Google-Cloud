package com.curiousbinary.pubsubconsumerdemo.controller;

import com.curiousbinary.pubsubconsumerdemo.service.PubSubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PubSubController {

    @Autowired
    private PubSubService pubSubService;
    private static final Logger log = LoggerFactory.getLogger(PubSubController.class);

    @GetMapping("/getmessages")
    public String getMessages() {
        log.info("Inside getMessage Controller");
        pubSubService.getGMessages();
        return "Shutting down the subscriber...";
    }
}
