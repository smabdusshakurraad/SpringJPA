package com.example.SpringBootJPA.repository;

import com.example.SpringBootJPA.entity.Course;
import com.example.SpringBootJPA.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    CourseMaterialRepository courseMaterialRepository;

    //Cascading is to pass properties or information from parent to child
    @Test
    public void saveCourseMaterial(){

        Course course = Course.builder()
                .title("JAVA")
                .credit(3)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.portechai.com")
                .course(course)
                .build();

        courseMaterialRepository.save(courseMaterial);
    }

    @Test
    public void printAllCourseMaterials(){
        List<CourseMaterial> materials =
                courseMaterialRepository.findAll();

        System.out.println("materials = " + materials);

    }
}