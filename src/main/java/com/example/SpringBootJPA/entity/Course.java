package com.example.SpringBootJPA.entity;

import com.example.SpringBootJPA.repository.CourseRepository;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Course { // course class

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;
    private String title;
    private Integer credit;

    @OneToOne(
            mappedBy = "course"
    ) //Bidirectional mapping
    private CourseMaterial courseMaterial;

    @ManyToOne( // preferred relation than one to many
            cascade = CascadeType.ALL
    )
    @JoinColumn( // unidirectional Many-to-one
            name = "teacher_Id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    //Many-to-Many Joining
    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable( // New Joining Table
            name = "course_studnet_mapping",
            joinColumns = @JoinColumn( // Joining column of this entity
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn( // Joining column of the entity we want to join
                    name = "student_id",
                    referencedColumnName = "studentId"
            )

    )
    private List<Student> students; // students list for many many joining

    public void addStudents(Student student){

        if(students == null) students = new ArrayList<>(); // arraylist to add student value
        students.add(student); // add student
    }

}
