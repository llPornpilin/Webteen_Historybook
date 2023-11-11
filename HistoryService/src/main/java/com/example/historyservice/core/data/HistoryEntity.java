package com.example.historyservice.core.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("Historybooks")
public class HistoryEntity {
    @Id
    private String historyBookId;
    private String bookId;
    private String chapterId;
    private Date date;
    private String userId;
}
