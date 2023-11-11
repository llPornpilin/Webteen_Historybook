package com.example.historyservice.core.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@Document("Book")
public class BookEntity implements Serializable {

    @Id
    private String bookId;
    private String title;
    private String description;
    private String category;
    private String type;
    private String cover;
    private Integer view;
    private Integer like;
    private Integer comment;
    private String status;
    private String userId;
}
