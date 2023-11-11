package com.example.historyservice.query;

import com.example.historyservice.core.data.HistoryEntity;
import com.example.historyservice.core.data.HistoryRepository;
import com.example.historyservice.core.events.HistoryCreateEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HistoryEventsHandler {
    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryEventsHandler(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @EventHandler
    public void on(HistoryCreateEvent event) {
        HistoryEntity historyEntity = new HistoryEntity();
        BeanUtils.copyProperties(event, historyEntity);
        historyRepository.insert(historyEntity);
    }
}
