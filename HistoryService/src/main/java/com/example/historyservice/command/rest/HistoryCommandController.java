package com.example.historyservice.command.rest;

import com.example.historyservice.query.rest.HistoryRestModel;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoryCommandController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/addHistory")
    public String addHistory(@RequestBody HistoryRestModel model) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        rabbitTemplate.convertAndSend("HistoryExchange", "addHistory", model);
        return "<<< Add History SUCCESS >>>";
    }
}
