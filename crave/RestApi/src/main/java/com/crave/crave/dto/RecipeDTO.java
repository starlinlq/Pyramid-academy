package com.crave.crave.dto;

import com.crave.crave.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
    private long id;
    private String title;
    private String description;
    private List<String> ingredients;
    private List<String> instructions;
    private Category category;
    private String userId;
    private LocalDate createdAt;

}
