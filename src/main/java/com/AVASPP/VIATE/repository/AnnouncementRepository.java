package com.AVASPP.VIATE.repository;

import com.AVASPP.VIATE.entity.Announcement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AnnouncementRepository extends CrudRepository<Announcement, Long> {
    @Query("SELECT id FROM Announcement ORDER BY id DESC LIMIT :amount")
    long[] getLastAnnouncementIDs(@Param("amount") int amount);
}
