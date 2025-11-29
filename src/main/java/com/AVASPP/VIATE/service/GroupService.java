package com.AVASPP.VIATE.service;

import com.AVASPP.VIATE.entity.Group;
import com.AVASPP.VIATE.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String getGroupNameById(long id) {
        return groupRepository.findById(id).get().getName();
    }

    public long CourseByGroupName(String name) {
        long course = 0;
        if(name.contains("25")) {
            course = 1;
        }

        return course;
    }
}
