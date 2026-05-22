package com.AVASPP.VIATE.service;

import com.AVASPP.VIATE.entity.Group;
import com.AVASPP.VIATE.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAllGroups() {
        return (List<Group>) groupRepository.findAll();
    }

    public Group findById(long id) {
        return groupRepository.findById(id).get();
    }

    public List<Group> getAllGroupsWithCourse(int course) {
        int year = LocalDateTime.now().getYear() % 100; //последние 2 цифры текущего года
        int offset = LocalDateTime.now().getMonth().getValue() < 9 ? 0 : 1; //проверка на четный семестр - Б25 всё ещё 1 курс в 2026 году
        year -= (course - offset);

        List<Group> group = (List<Group>) groupRepository.findAllByCourse(year);

        return group;
    }

    public String getGroupNameById(long id) {
        return groupRepository.findById(id).get().getName();
    }
}
