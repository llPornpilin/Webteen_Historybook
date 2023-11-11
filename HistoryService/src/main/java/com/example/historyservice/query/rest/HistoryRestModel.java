package com.example.historyservice.query.rest;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Data
public class HistoryRestModel {
    private String historyBookId;
    private String bookId;
    private String chapterId;
    private Date date;
    private String userId;
}
