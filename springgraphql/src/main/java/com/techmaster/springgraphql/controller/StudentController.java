package com.techmaster.springgraphql.controller;

import com.techmaster.springgraphql.model.Student;
import com.techmaster.springgraphql.service.StudentService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @QueryMapping
    public List<Student> students() {
        return studentService.getAllStudents();
    }

    @QueryMapping
    public Student student(@Argument String id) {
        return studentService.getStudentById(id);
    }

    @MutationMapping
    public Student addStudent(@Argument("input") Student input) {
        return studentService.addStudent(input);
    }
} 