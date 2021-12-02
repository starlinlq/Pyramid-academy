package com.starlinlq.springdatajpa.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="tbl_student", uniqueConstraints = @UniqueConstraint(name="emailid_unique", columnNames = "email_address"))
public class Student {
    @Id
    @SequenceGenerator(name="student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    @Column(name="id")
    private Long studentId;
    private String firstName;
    private String lastName;
    @Column(name="email_address", nullable = false)
    private String emailId;
    private String guardianName;
    private String guardianEmail;
    private String guardianMobile;
}
