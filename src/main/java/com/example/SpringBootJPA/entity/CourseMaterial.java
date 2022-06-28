package com.example.SpringBootJPA.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course") // excluding course from toString method
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

    // courseMaterial and course have one-to-one relation
    @OneToOne(
            cascade = CascadeType.ALL, //Cascading is to pass properties or information from parent to child
            fetch = FetchType.LAZY, // fetch data without foreign key value
            optional = false // by default all the courses are optional
    ) // relationship
    @JoinColumn( // foreign column
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course; //join with course

}
