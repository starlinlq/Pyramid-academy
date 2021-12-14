package com.crave.crave.service;

import com.crave.crave.customResponse.RecipeSearchRes;
import com.crave.crave.dto.RecipeDTO;
import com.crave.crave.exception.InvalidCategoryException;
import com.crave.crave.model.Category;
import com.crave.crave.model.Recipe;
import com.crave.crave.model.User;
import com.crave.crave.repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements  RecipeService {
    @Autowired
    private RecipeRepo recipeRepo;
    @Autowired
    private CategoryService categoryService;

    @Override
    public RecipeDTO saveRecipe(Recipe recipe, User user)  {
        Optional<Category> category = categoryService.findById(recipe.getCategory().getId());

        if(category.isPresent()){
            recipe.setUser(user);
            recipe.setCategory(category.get());
            recipe.setCreatedAt(LocalDate.now());
            Recipe newRecipe = recipeRepo.save(recipe);
            category.get().setRecipes(newRecipe);
            return  RecipeMapper.toRecipeDTO(newRecipe);
        }
        return null;

    }

    @Override
    public List<RecipeDTO> findByUserId(long userId, int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Recipe> pages = recipeRepo.findByUserId(userId, paging);

        if(pages.hasContent()){
            return pages.getContent().stream().map(RecipeMapper::toRecipeDTO).collect(Collectors.toList());
        } else
            return new ArrayList<>();
    }

    @Override
    public List<RecipeDTO> getAll(int pageNo, int pageSize, String sortBy ){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Recipe>  pagedResult = recipeRepo.findAll(paging);

        if(pagedResult.hasContent()){
            return pagedResult.getContent().stream().map(RecipeMapper::toRecipeDTO).collect(Collectors.toList());
        } else
            return new ArrayList<RecipeDTO>();

    }

    @Override
    public List<RecipeSearchRes> findByTitleContaining(String title, int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Recipe> recipes = recipeRepo.findByTitleContainingIgnoreCase(title, paging);

        if(recipes.hasContent()){
           return recipes.getContent()
                   .stream()
                   .map(recipe -> new RecipeSearchRes(recipe.getTitle(), recipe.getId(), recipe.getUser().getUsername(), recipe.getCategory()))
                   .collect(Collectors.toList());
        } else
           return new ArrayList<RecipeSearchRes>();
    }

    @Override
    public Optional<RecipeDTO> findById(long id) {
        Optional<Recipe> recipe = recipeRepo.findById(id);
        return recipe.map(RecipeMapper::toRecipeDTO);
    }

    @Override
    public void removeById(long id) {
        recipeRepo.deleteById(id);
    }

    @Override
    public Recipe update(Recipe recipe) {
        return recipeRepo.save(recipe);
    }

    static class RecipeMapper {
        public static RecipeDTO toRecipeDTO(Recipe recipe){
            RecipeDTO recipeDTO = new RecipeDTO();
            recipeDTO.setDescription(recipe.getDescription());
            recipeDTO.setId(recipe.getId());
            recipeDTO.setTitle(recipe.getTitle());
            recipeDTO.setIngredients(recipe.getIngredients());
            recipeDTO.setInstructions(recipe.getInstructions());
            recipeDTO.setCategory(recipe.getCategory());
            recipeDTO.setUserId(recipe.getUser().getUsername());
            recipeDTO.setCreatedAt(recipe.getCreatedAt());
            return recipeDTO;
        }

    }
}
