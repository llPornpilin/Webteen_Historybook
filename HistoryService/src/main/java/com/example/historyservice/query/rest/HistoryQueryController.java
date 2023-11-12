package com.example.historyservice.query.rest;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/getChapter")
    public ArrayList getBooks() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Object chapter = rabbitTemplate.convertSendAndReceive("HistoryExchange", "getChapter", "");
        return (ArrayList) chapter;
    }

    @GetMapping(value = "/getChapter/{chapterId}")
    public ChapterRestModel getChapterByChapterId(@PathVariable("chapterId") String chapterId) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Object book = rabbitTemplate.convertSendAndReceive("HistoryExchange", "getChapterId", chapterId);
        return (ChapterRestModel) book;
    }
}
