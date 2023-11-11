package com.example.historyservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;

@Builder
@Data
public class CreateHistoryCommand {
    @TargetAggregateIdentifier
    private final String historyBookId;
    private final String bookId;
    private final String chapterId;
    private final Date date;
    private final String userId;
}
