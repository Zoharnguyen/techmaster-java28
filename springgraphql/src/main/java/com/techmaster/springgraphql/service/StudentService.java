package com.techmaster.springgraphql.service;

import com.techmaster.springgraphql.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudentById(String id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Student addStudent(Student student) {
        student.setId(String.valueOf(idCounter.getAndIncrement()));
        students.add(student);
        return student;
    }
} 