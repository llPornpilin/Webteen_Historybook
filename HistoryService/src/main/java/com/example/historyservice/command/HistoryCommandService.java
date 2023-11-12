package com.example.historyservice.command;

import com.example.historyservice.command.rest.HistoryRestModel;
import com.example.historyservice.core.data.HistoryRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class HistoryCommandService {
    @Autowired
    private final CommandGateway commandGateway;

    @Autowired
    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryCommandService(CommandGateway commandGateway, HistoryRepository historyRepository) {
        this.commandGateway = commandGateway;
        this.historyRepository = historyRepository;
    }

    @RabbitListener(queues = "AddHistoryQueue")
    public void addHistory(HistoryRestModel model) {
        CreateHistoryCommand command = CreateHistoryCommand.builder()
                .historyBookId(UUID.randomUUID().toString())
                .bookId(model.getBookId())
                .chapterId(model.getChapterId())
                .date(model.getDate())
                .userId(model.getUserId())
                .build();

        try {
            commandGateway.sendAndWait(command);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
