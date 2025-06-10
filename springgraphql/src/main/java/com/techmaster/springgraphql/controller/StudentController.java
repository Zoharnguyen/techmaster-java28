package com.techmaster.springgraphql.controller;

import com.techmaster.springgraphql.model.Student;
import com.techmaster.springgraphql.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @QueryMapping
    public List<Student> allStudents() {
        return studentService.getAllStudents();
    }

    @QueryMapping
    public Student studentById(@Argument Integer id) {
        return studentService.getStudentById(id);
    }
} 