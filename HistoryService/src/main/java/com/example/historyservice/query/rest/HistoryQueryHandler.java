package com.example.historyservice.query.rest;

import com.example.historyservice.core.data.ChapterEntity;
import com.example.historyservice.core.data.ChapterRepository;
import com.example.historyservice.core.data.HistoryEntity;
import com.example.historyservice.core.data.HistoryRepository;
import com.example.historyservice.query.FindChaptersQuery;
import com.example.historyservice.query.FindHistoryQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class HistoryQueryHandler {
    private final ChapterRepository chapterRepository;
    private final HistoryRepository historyRepository;

    public HistoryQueryHandler(ChapterRepository chapterRepository, HistoryRepository historyRepository) {
        this.chapterRepository = chapterRepository;
        this.historyRepository = historyRepository;
    }

    @QueryHandler
    public List<ChapterRestModel> findBooks(FindChaptersQuery query) {
        List<ChapterRestModel> bookRest = new ArrayList<>();
        List<ChapterEntity> storedBooks = chapterRepository.findAll();
        for (ChapterEntity chapterEntity : storedBooks) {
            ChapterRestModel chapterRestModel = new ChapterRestModel();
            BeanUtils.copyProperties(chapterEntity, chapterRestModel);
            bookRest.add(chapterRestModel);
        }
        return bookRest;
    }


    public class HistoryDateComparator implements Comparator<HistoryRestModel> {
        @Override
        public int compare(HistoryRestModel h1, HistoryRestModel h2) {
            return h1.getDate().compareTo(h2.getDate());
        }
    }
    @QueryHandler
    public List<HistoryRestModel> findHistory(FindHistoryQuery query) {
        List<HistoryRestModel> recentRestModel = new ArrayList<>();
        List<HistoryEntity> storedBooks = historyRepository.findAll();
        for (HistoryEntity historyEntity : storedBooks) {
            HistoryRestModel historyRestModel = new HistoryRestModel();
            BeanUtils.copyProperties(historyEntity, historyRestModel);
            recentRestModel.add(historyRestModel);
        }
        recentRestModel.sort(new HistoryDateComparator());
        return recentRestModel;
    }
}
