package com.example.historyservice.core.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends MongoRepository<ChapterEntity, String> {
    @Query(value = "{'bookId': ?0, 'chapterId': ?1}")
    public ChapterEntity findBookEntitiesByBookIdAndChapterId(String bookId, String chapterId);

    @Query(value = "{'chapterId': ?0}")
    public ChapterEntity findChapterEntitiesByChapterId(String chapterId);
}
