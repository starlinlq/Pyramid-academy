package recipes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.entity.Recipe;
import recipes.repositories.RecipeRepo;

@Service
public class RecipeService {
  @Autowired
  private RecipeRepo repo;

    public Recipe save(Recipe recipe){
      repo.save(recipe);
      return recipe;
    }

    public Recipe getSingleRecipe(long id){
       return repo.getById(id);
    }

}
