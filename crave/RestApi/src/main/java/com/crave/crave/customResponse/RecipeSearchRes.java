package com.crave.crave.customResponse;

import com.crave.crave.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeSearchRes {
    private String title;
    private long id;
    private String userId;
    private Category category;
}
