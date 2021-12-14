package com.crave.crave.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROFILE")
public class Profile {
    @Id
    @GeneratedValue
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String photoUrl;
    @NotBlank
    private String status;

    @OneToOne(mappedBy = "profile")
    private User user;

}
