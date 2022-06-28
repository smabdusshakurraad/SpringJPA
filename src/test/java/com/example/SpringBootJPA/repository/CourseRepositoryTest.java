package com.example.SpringBootJPA.repository;

import com.example.SpringBootJPA.entity.Course;
import com.example.SpringBootJPA.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> courses =
                courseRepository.findAll();

        System.out.println("courses = " + courses);
    }

    @Test
    public void  saveCourseWithTeacher(){ // To test many-to-one relation
        Teacher teacher = Teacher.builder()
                .firstName("Mehedi")
                .LastName("Hassan")
                .build();
        Course course =
                Course.builder()
                        .title("Thesis")
                        .credit(6)
                        .teacher(teacher)
                        .build();

        courseRepository.save(course);
    }
}