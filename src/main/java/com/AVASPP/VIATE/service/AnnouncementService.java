package com.AVASPP.VIATE.service;

import com.AVASPP.VIATE.entity.Announcement;
import com.AVASPP.VIATE.entity.News;
import com.AVASPP.VIATE.repository.AnnouncementRepository;
import com.AVASPP.VIATE.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService {
    @Autowired
    private final AnnouncementRepository announcementRepository;

    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public Announcement findByID(long id) {
        return announcementRepository.findById(id).get();
    }

    public long[] getLastAnnouncementIDs(int amount) {
        return announcementRepository.getLastAnnouncementIDs(amount);
    }

    public List<Announcement> getLastAnnouncements(int amount) {
        long[] IDs = getLastAnnouncementIDs(amount);
        List<Announcement> announcements = new ArrayList<Announcement>();

        for (int i = 0; i < IDs.length; i++) {
            announcements.add(announcementRepository.findById(IDs[i]).get());
        }
        return announcements;
    }
}
