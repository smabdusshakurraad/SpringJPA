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


    @Test //Testing default method of JPA
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

    @Test //Testing default method of JPA
    public void printAllStudent(){
        List <Student> studentList =
                studentRepository.findAll();

        System.out.println("Studnets list = "+studentList);
    }


    @Test //Testing default method of JPA, need to declare inside repo
    public void printAllStudentByFirstName(){
        List <Student> studentList =
                studentRepository.findByFirstName("zayan");

        System.out.println("Studnets list = "+studentList);
    }

    @Test //Testing default method of JPA, need to declare inside repo
    public void printAllStudentByFirstNameContaining(){
        List <Student> studentList =
                studentRepository.findByFirstNameContaining("ay");

        System.out.println("Studnets list = "+studentList);
    }
    
    @Test //Testing default method of JPA, need to declare inside repo
    public void printAllStudentWithLastName(){
        List <Student> students = studentRepository.findByLastNameNotNull();
        System.out.println("students = " + students);
    }
    
    @Test //Testing default method of JPA, need to declare inside repo
    public void printStudentByParentName(){
        List<Student> students = studentRepository.findByParentName("Bill");
        System.out.println("students = " + students);
    }
    
    @Test //Testing default method of JPA, need to declare inside repo
    public void printStudentByFirstAndLastName(){
        List<Student> students = studentRepository.findByFirstNameAndLastName("zayan","myza");
        System.out.println("students = " + students);
    }

    @Test //Testing JPQL method
    public void printStudentByEmail(){
        Student student =
        studentRepository.getStudentByEmailId("xyz@email.com");

        System.out.println("students = " + student);
    }
    
    @Test //Testing JPQL method
    public void printStudentFirstNameByEmailId(){
        String firstName =
                studentRepository.getStudentFirstNameByEmailId("xyz@email.com");
        System.out.println("firstName = " + firstName);
    }

    @Test //Testing native SQL method
    public void printStudentByEmailIdNative(){
        String student =
                studentRepository.getStudentFirstNameByEmailNative("xyz@email.com");

        System.out.println("student = " + student);
    }


    @Test //Testing native SQL method using named param
    public void printStudentByEmailIdNativeParam(){
        Student student =
                studentRepository.getStudentByEmailNativeParam("xyz@email.com");

        System.out.println("student = " + student);
    }

    @Test //Testing native SQL method for modifying and transaction using named param
    public void updateStudentFirstNameByEmailParam(){
        int update =
                studentRepository.updateFirstNameByEmail("Myza", "xyz@email.com");

        System.out.println("student = " + update);
    }

}