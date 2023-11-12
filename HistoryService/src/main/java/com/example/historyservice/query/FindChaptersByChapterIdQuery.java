package com.example.historyservice.query;

public class FindChaptersByChapterIdQuery {
    private final String chapterId;

    public FindChaptersByChapterIdQuery(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterId() {
        return chapterId;
    }
}
