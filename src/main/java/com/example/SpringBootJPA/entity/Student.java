package com.example.SpringBootJPA.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Data // For properties of this class using spring lombok
@AllArgsConstructor // To generate all argument constructor
@NoArgsConstructor // To generate no argument constructor
@Builder // Builder to create builder object data
@Table(name = "student_tbl", // Defining table name
        uniqueConstraints = @UniqueConstraint( // Definig unique constraints
                name = "emailid_unique",
                columnNames = "email_address"
        )
)
public class Student {

    @Id
    @SequenceGenerator( //declaring the sequence
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue( // using the sequence to generate values
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    private Long studentId;
    private String firstName;
    private String lastName;

    @Column(name = "email_address",
            nullable = false)//defining column
    private String email;
//    private String parentName;
//    private String parentEmail;
//    private String parentMobile;

    @Embedded
    private Parent parent;


}
