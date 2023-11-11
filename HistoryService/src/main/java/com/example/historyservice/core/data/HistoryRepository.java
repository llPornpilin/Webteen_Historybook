package com.example.historyservice.core.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends MongoRepository<HistoryEntity, String> {
    @Query(value = "{ 'historyBookId' : ?0 }")
    public HistoryEntity findHistoryEntitiesByHistoryBookId(String historyBookId);
}
