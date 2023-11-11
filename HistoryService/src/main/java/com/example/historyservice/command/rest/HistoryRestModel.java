package com.example.historyservice.command.rest;

import lombok.Data;

import java.util.Date;

@Data
public class HistoryRestModel {
    private String historyBookId;
    private String bookId;
    private String chapterId;
    private Date date;
    private String userId;
}
