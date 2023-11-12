package com.example.historyservice.core.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("Chapter")
public class ChapterEntity implements Serializable {
    @Id
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
