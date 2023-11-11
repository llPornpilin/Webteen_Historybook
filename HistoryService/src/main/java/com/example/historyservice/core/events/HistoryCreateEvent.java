package com.example.historyservice.core.events;

import lombok.Data;

import java.util.Date;

@Data
public class HistoryCreateEvent {
    private String historyBookId;
    private String bookId;
    private String chapterId;
    private Date date;
    private String userId;
}
