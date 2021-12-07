package com.crave.crave.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="CATEGORY")
public class Category {
    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "category")
    @ElementCollection
    private List<Recipe> recipes;
}
