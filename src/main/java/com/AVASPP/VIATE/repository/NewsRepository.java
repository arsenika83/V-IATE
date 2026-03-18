package com.AVASPP.VIATE.repository;

import com.AVASPP.VIATE.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT id FROM News ORDER BY id DESC LIMIT :amount")
    long[] getLastArticleIDs(@Param("amount") int amount);
}
