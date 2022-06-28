package com.example.SpringBootJPA.repository;

import com.example.SpringBootJPA.entity.Student;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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
    Student getStudentByEmailId(String email);

    //JPQL
    @Query("select s.firstName from Student s where s.email =?1 ")
    String getStudentFirstNameByEmailId(String email);


    //Native query
    @Query(
            value = "select s.firstname from student_tbl s where email_address = ?1",
            nativeQuery = true
    )
    String getStudentFirstNameByEmailNative(String email);


    //Named Param
    @Query(value = "select * from student_tbl s where email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailNativeParam(@Param("emailId") String email);


    @Modifying // to modify database
    @Transactional // For setting up query and checking all the things then transaction to DB
    @Query(
            value = "update student_tbl set first_name = :firstName where email_address = :emailId",
            nativeQuery = true
    )
    int updateFirstNameByEmail(@Param("firstName") String firstName, @Param("emailId") String email);


}
