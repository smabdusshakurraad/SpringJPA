package com.example.SpringBootJPA.entity;

import com.example.SpringBootJPA.repository.CourseRepository;
import lombok.*;

import javax.persistence.*;

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

}
