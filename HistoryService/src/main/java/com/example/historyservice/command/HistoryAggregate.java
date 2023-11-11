package com.example.historyservice.command;

import com.example.historyservice.core.events.HistoryCreateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Aggregate
public class HistoryAggregate {
    @AggregateIdentifier
    private String historyBookId;
    private String bookId;
    private String chapterId;
    private Date date;
    private String userId;

    public HistoryAggregate(){}

    @CommandHandler
    public HistoryAggregate (CreateHistoryCommand createHistoryCommand) {
        HistoryCreateEvent historyCreateEvent = new HistoryCreateEvent();
        BeanUtils.copyProperties(createHistoryCommand, historyCreateEvent);
        AggregateLifecycle.apply(historyCreateEvent);
    }

    @EventSourcingHandler
    public void on(HistoryCreateEvent historyCreateEvent) {
        this.historyBookId = historyCreateEvent.getHistoryBookId();
        this.bookId = historyCreateEvent.getBookId();
        this.chapterId = historyCreateEvent.getChapterId();
        this.date = historyCreateEvent.getDate();
        this.userId = historyCreateEvent.getUserId();
    }
}
