package recipes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.entity.Recipe;
import recipes.repositories.RecipeRepo;

import java.util.Optional;

@Service
public class RecipeService {

  @Autowired
  private RecipeRepo repo;

    public Recipe save(Recipe recipe){
      return repo.save(recipe);
    }

    public Recipe getSingleRecipe(long id){
        Optional<Recipe> recipeOptional= repo.findById(id);
        return recipeOptional.orElse(null);
    }


    public boolean deleteRecipeById(long id){

       if(repo.existsById(id)){
           repo.deleteById(id);
           return true;
       }

       return false;
    }

}
