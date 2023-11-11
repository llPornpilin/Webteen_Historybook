package com.example.historyservice.query.rest;

import com.example.historyservice.core.data.BookEntity;
import com.example.historyservice.core.data.BookRepository;
import com.example.historyservice.core.data.HistoryEntity;
import com.example.historyservice.core.data.HistoryRepository;
import com.example.historyservice.query.FindBooksQuery;
import com.example.historyservice.query.FindHistoryQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class HistoryQueryHandler {
    private final BookRepository bookRepository;
    private final HistoryRepository historyRepository;

    public HistoryQueryHandler(BookRepository bookRepository, HistoryRepository historyRepository) {
        this.bookRepository = bookRepository;
        this.historyRepository = historyRepository;
    }

    @QueryHandler
    public List<BookRestModel> findBooks(FindBooksQuery query) {
        List<BookRestModel> bookRest = new ArrayList<>();
        List<BookEntity> storedBooks = bookRepository.findAll();
        for (BookEntity bookEntity : storedBooks) {
            BookRestModel bookRestModel = new BookRestModel();
            BeanUtils.copyProperties(bookEntity, bookRestModel);
            bookRest.add(bookRestModel);
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
