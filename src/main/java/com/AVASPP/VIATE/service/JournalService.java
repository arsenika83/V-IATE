package com.AVASPP.VIATE.service;

import com.AVASPP.VIATE.entity.Journal;
import com.AVASPP.VIATE.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {

    @Autowired
    private final JournalRepository journalRepository;

    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    public Journal findById(long id) {
        return journalRepository.findById(id).get();
    }

    public List<Journal> findByGroupId(long g_id) {
        return (List<Journal>) journalRepository.findAllByGroupId(g_id);
    }
}
