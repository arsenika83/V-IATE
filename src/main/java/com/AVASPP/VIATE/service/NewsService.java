package com.AVASPP.VIATE.service;

import com.AVASPP.VIATE.entity.News;
import com.AVASPP.VIATE.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {
    @Autowired
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public News findById(long id) {
        return newsRepository.findById(id).get();
    }

    public long[] getLastArticleIDs(int amount) {
        return newsRepository.getLastArticleIDs(amount);
    }

    public List<News> getLastArticles(int amount) {
        long[] IDs = getLastArticleIDs(amount);
        List<News> news = new ArrayList<News>();

        for (int i = 0; i < IDs.length; i++) {
            news.add(newsRepository.findById(IDs[i]).get());
        }

        return news;
    }
}
