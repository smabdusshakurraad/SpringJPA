package com.example.SpringBootJPA.repository;

import com.example.SpringBootJPA.entity.Course;
import com.example.SpringBootJPA.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){ // To test one-to-many relationship
        Course courseDSA =
                Course.builder()
                        .title("DSA")
                        .credit(3)
                        .build();
        Course courseIDB =
                Course.builder()
                        .title("IDB")
                        .credit(3)
                        .build();
        Teacher teacher =
                Teacher.builder()
                        .firstName("Abhijit")
                        .LastName("Bhowmik")
                        //.courses(List.of(courseIDB, courseDSA))
                        .build();

        teacherRepository.save(teacher);
    }

}