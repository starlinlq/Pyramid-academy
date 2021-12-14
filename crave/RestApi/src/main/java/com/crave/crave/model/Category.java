package com.crave.crave.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name="CATEGORY")
public class Category {
    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @Column(unique = true)
    private String name;


    @OneToMany(mappedBy = "category")
    private List<Recipe> recipes = new ArrayList<>();

    public Category(String name){
        this.name = name;
    }

    @JsonIgnore
    public List<Recipe> getRecipes() {
        return recipes;
    }


    public void setRecipes(Recipe newRecipe) {
        this.recipes.add(newRecipe);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
