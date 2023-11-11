package com.example.historyservice.query;

import com.example.historyservice.query.rest.BookRestModel;
import com.example.historyservice.query.rest.HistoryRestModel;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryQueryService {
    @Autowired
    private QueryGateway queryGateway;

    public HistoryQueryService(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @RabbitListener(queues = "GetBookQueue")
    public List<BookRestModel> getBook() {
        System.out.println("GET ALL BOOKS");
        FindBooksQuery findBooksQuery = new FindBooksQuery();
        return queryGateway.query(
                findBooksQuery,
                ResponseTypes.multipleInstancesOf(BookRestModel.class)
        ).join();
    }

    @RabbitListener(queues = "GetHistoryQueue")
    public List<HistoryRestModel> getHistory() {
        FindHistoryQuery findHistoryQuery = new FindHistoryQuery();
        return queryGateway.query(
                findHistoryQuery,
                ResponseTypes.multipleInstancesOf(HistoryRestModel.class)
        ).join();
    }
}
