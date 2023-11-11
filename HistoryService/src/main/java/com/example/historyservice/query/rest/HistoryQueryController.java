package com.example.historyservice.query.rest;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class HistoryQueryController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @GetMapping(value = "/getHistory")
    public ArrayList getHistory() {
        System.out.println("get History");
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Object historyBook = rabbitTemplate.convertSendAndReceive("HistoryExchange", "getHistory", "");
        return (ArrayList) historyBook;
    }

    @GetMapping("/getBook")
    public ArrayList getBooks() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Object book = rabbitTemplate.convertSendAndReceive("HistoryExchange", "getBook", "");
        return (ArrayList) book;
    }
}
