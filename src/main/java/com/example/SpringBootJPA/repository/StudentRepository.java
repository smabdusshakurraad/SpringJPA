package com.example.SpringBootJPA.repository;

import com.example.SpringBootJPA.entity.Student;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    //using build in methods of JPA repository
    List<Student> findByFirstName(String firstName);
    List<Student> findByFirstNameContaining(String name);
    List<Student> findByLastNameNotNull();
    List<Student> findByParentName(String name);

    List<Student> findByFirstNameAndLastName(String firstName,
                                             String lastName);

    //JPQL query to get custom data
    @Query("select s from Student s where s.email = ?1")
    List<Student> getStudentByEmailId(String email);



}
