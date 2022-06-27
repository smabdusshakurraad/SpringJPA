package com.example.SpringBootJPA.repository;

import com.example.SpringBootJPA.entity.Parent;
import com.example.SpringBootJPA.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@DataJpaTest // Test the repository using data and will flash those
// after finishing testing without impacting the table
class StudentRepositoryTest {
    //testing student repo

    @Autowired
    private StudentRepository studentRepository;

//    @Test
//    public void saveStudent() {
//        //building student obj using parent details as attributes of the student class
//        Student student = Student.builder()
//                .email("xyz@email.com")
//                .firstName("xaya")
//                .lastName("silk")
//                .parentName("Bill")
//                .parentMobile("012345678910")
//                .parentEmail("abc@gmail.com")
//                .build();
//
//        //using method of repository
//        studentRepository.save(student);
//
//    }

    @Test
    public void saveStudent() {
        //building student obj using parent obj

        Parent parent = Parent.builder()
                .name("Bill")
                .email("abc@gmail.com")
                .mobile("012345678910")
                .build();
        Student student = Student.builder()
                .email("zm@email.com")
                .firstName("zayan")
                .lastName("myza")
                .parent(parent)
                .build();

        //using method of repository
        studentRepository.save(student);

    }

    @Test
    public void printAllStudent(){
        List <Student> studentList =
                studentRepository.findAll();

        System.out.println("Studnets list = "+studentList);
    }


    @Test
    public void printAllStudentByFirstName(){
        List <Student> studentList =
                studentRepository.findByFirstName("zayan");

        System.out.println("Studnets list = "+studentList);
    }

    @Test
    public void printAllStudentByFirstNameContaining(){
        List <Student> studentList =
                studentRepository.findByFirstNameContaining("ay");

        System.out.println("Studnets list = "+studentList);
    }
    
    @Test
    public void printAllStudentWithLastName(){
        List <Student> students = studentRepository.findByLastNameNotNull();
        System.out.println("students = " + students);
    }
    
    @Test
    public void printStudentByParentName(){
        List<Student> students = studentRepository.findByParentName("Bill");
        System.out.println("students = " + students);
    }
    
    @Test
    public void printStudentByFirstAndLastName(){
        List<Student> students = studentRepository.findByFirstNameAndLastName("zayan","myza");
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByEmail(){
        List<Student> students =
        studentRepository.getStudentByEmailId("xyz@email.com");

        System.out.println("students = " + students);
    }

}