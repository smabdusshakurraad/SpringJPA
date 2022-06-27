package com.example.SpringBootJPA.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

//to set column name of the table
@AttributeOverrides({
        @AttributeOverride(
                name = "name",
                column = @Column(name = "parent_name")
        ),

        @AttributeOverride(
                name = "email",
                column = @Column(name = "parent_email")
        ),

        @AttributeOverride(
                name = "mobile",
                column = @Column(name = "parent_mobile")
        )}
)
public class Parent{
    private String name;
    private String email;
    private String mobile;
}
