package com.AVASPP.VIATE.service;

import com.AVASPP.VIATE.entity.StudentStatistic;
import com.AVASPP.VIATE.repository.StudentStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentStatisticService {
    @Autowired
    private final StudentStatisticRepository studentStatisticRepository;

    public StudentStatisticService(StudentStatisticRepository studentStatisticRepository) {
        this.studentStatisticRepository = studentStatisticRepository;
    }

    public StudentStatistic findById(long id) {
        return studentStatisticRepository.findById(id).get();
    }

    public StudentStatistic findByStudentIdAndJournalId(long s_id, long j_id) {
        return studentStatisticRepository.findByStudentIdAndJournalId(s_id, j_id);
    }

    public List<Integer> getMarksByStudentId(long id) {
        return studentStatisticRepository.getMarksByStudentId(id);
    }

    public int[] estimateGradesByStudentId(long id) {
        int[] grades = new int[4];
        List<Integer> marks = getMarksByStudentId(id);

        int three = 0;
        int four = 0;
        int five = 0;

        for (int i = 0; i < marks.size(); i++) {
            if(marks.get(i) >= 90) {
                five++;
            }
            else if(marks.get(i) >= 70) {
                four++;
            }
            else if(marks.get(i) >= 60) {
                three++;
            }
        }

        grades[0] = three;
        grades[1] = four;
        grades[2] = five;
        grades[3] = three + four + five;

        return grades;
    }

}
