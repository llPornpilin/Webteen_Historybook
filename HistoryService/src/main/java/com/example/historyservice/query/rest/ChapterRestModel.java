package com.example.historyservice.query.rest;

import lombok.Data;

@Data
public class ChapterRestModel {
    private String chapterId;
    private String number;
    private String title;
    private String content;
    private String cover;
    private String view;
    private String like;
    private String date;
    private String bookId;
    private String createdAt;
    private String updatedAt;
}
