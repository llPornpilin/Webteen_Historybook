package com.example.historyservice.query;

import com.example.historyservice.query.rest.BookRestModel;
import com.example.historyservice.query.rest.ChapterRestModel;
import com.example.historyservice.query.rest.HistoryRestModel;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
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

    @RabbitListener(queues = "GetChapterQueue")
    public List<ChapterRestModel> getBook() {
        System.out.println("GET ALL Chapter");
        FindChaptersQuery findChaptersQuery = new FindChaptersQuery();
        return queryGateway.query(
                findChaptersQuery,
                ResponseTypes.multipleInstancesOf(ChapterRestModel.class)
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

    @RabbitListener(queues = "GetChapterIdQueue")
    public ChapterRestModel getChapterByChapterId(String chapterId) {
        System.out.println("GET BOOK BY BOOK ID: " + chapterId);

        FindChaptersByChapterIdQuery findChaptersByChapterIdQuery = new FindChaptersByChapterIdQuery(chapterId);
        return queryGateway.query(
                findChaptersByChapterIdQuery,
                ResponseTypes.instanceOf(ChapterRestModel.class)
        ).join();
    }
}
