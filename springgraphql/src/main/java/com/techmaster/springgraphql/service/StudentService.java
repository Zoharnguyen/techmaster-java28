package com.techmaster.springgraphql.service;

import com.techmaster.springgraphql.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StudentService {
    private final Map<Integer, Student> students = new ConcurrentHashMap<>();

    public StudentService() {
        // Initialize with some sample data
        students.put(1, new Student(1, "John Doe", "john@example.com", 2023));
        students.put(2, new Student(2, "Jane Smith", "jane@example.com", 2024));
        students.put(3, new Student(3, "Bob Johnson", "bob@example.com", 2023));
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    public Student getStudentById(Integer id) {
        return students.get(id);
    }
} 