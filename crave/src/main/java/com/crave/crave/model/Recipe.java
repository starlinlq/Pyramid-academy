package com.crave.crave.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="RECIPE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue
    private String id;
    @Size(min = 5)
    private String title;
    @Size(min = 1)
    @Size(min= 1)
    @ElementCollection
    private List<String> instructions;
    @Size(min=1)
    @ElementCollection
    private List<String> ingredients;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
