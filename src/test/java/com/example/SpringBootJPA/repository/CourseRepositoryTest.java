package com.example.SpringBootJPA.repository;

import com.example.SpringBootJPA.entity.Course;
import com.example.SpringBootJPA.entity.Parent;
import com.example.SpringBootJPA.entity.Student;
import com.example.SpringBootJPA.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

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

    @Test
    public void findAllPagination(){ // pagination without sorting
        //springframework.data.domain
       org.springframework.data.domain.Pageable firstPageWithThreeRecords
                =  PageRequest.of(0,3); // First page with 3 elements per page

        Pageable secondPageWithTwoRecords
                =  PageRequest.of(1,2); // 2nd page with 2 elements per page

        List<Course> courses =
                courseRepository.findAll(firstPageWithThreeRecords)
                        .getContent(); // get the contents based on pagination

        long totalElements = courseRepository.findAll(firstPageWithThreeRecords)
                .getTotalElements(); // get elements number per page based on pagination

        long totalPages =
                courseRepository.findAll( firstPageWithThreeRecords)
                        .getTotalPages(); // get total page numbers based on pagination

        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        System.out.println("courses = " + courses);


        courses = courseRepository.findAll(secondPageWithTwoRecords)
                .getContent();

        totalElements = courseRepository.findAll(secondPageWithTwoRecords)
                .getTotalElements();

        totalPages = courseRepository.findAll(secondPageWithTwoRecords)
                .getTotalPages();

        System.out.println("courses = " + courses);
        System.out.println("totalElements = " + totalElements);
        System.out.println("totalPages = " + totalPages);


    }

    @Test
    public void findBySorting(){ // Pagination with sorting
        Pageable firstPagrwithTwoRecords =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title")
                );

        Pageable secondPagewithTwoRecords =
                PageRequest.of(
                        1,
                        2,
                        Sort.by("credit").descending()
                );
        
        List<Course> courses =
                courseRepository.findAll(firstPagrwithTwoRecords)
                        .getContent();

        System.out.println("courses = " + courses);

        courses = courseRepository.findAll(secondPagewithTwoRecords)
                .getContent();

        System.out.println("courses = " + courses);
        
    }


    @Test
    public void findByTitleContaining(){ //To test Custom Pagination method

        Pageable firstPage =
                PageRequest.of(
                        0,
                        9
                );

        List<Course> courses =
                courseRepository.findByTitleContaining("D",
                        firstPage).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){ // To test many to many relation between course and student
        
        Teacher teacher = Teacher.builder()
                .firstName("Abdus")
                .LastName("Shakur")
                .build();

        Parent parent = Parent.builder()
                .name("Noor-a-Aysha")
                .email("noor@gmail.com")
                .mobile("019436895654")
                .build();

        Student student = Student.builder()
                .firstName("Raiyan")
                .lastName("Khandakar")
                .email("Khandakar@gmail.com")
                .parent(parent)
                .build();
        
        Course course = Course.builder()
                .title("Algo")
                .credit(3)
                .teacher(teacher)
                .build();
        
        course.addStudents(student);

        courseRepository.save(course);
//        System.out.println("course = " + course);
    }
}