package com.crave.crave.service;

import com.crave.crave.customResponse.RecipeSearchRes;
import com.crave.crave.dto.RecipeDTO;
import com.crave.crave.model.Recipe;
import com.crave.crave.model.User;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    List<RecipeDTO> getAll(int pageNo, int pageSize, String sortBy);
    RecipeDTO saveRecipe(Recipe recipe, User user);
    Optional<RecipeDTO> findById(long id);
    void removeById(long id);
    Recipe update(Recipe recipe);
    List<RecipeSearchRes> findByTitleContaining(String title, int pageNo, int pageSize);
    List<RecipeDTO> findByUserId(long userId, int pageNo, int pageSize);
}
