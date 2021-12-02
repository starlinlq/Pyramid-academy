package recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipes.entity.Recipe;
import recipes.services.RecipeService;

@RestController
@RequestMapping("/api/recipe")
public class RecipesController {
    private final RecipeService recipeService;

    @Autowired
    RecipesController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getSingleRecipe(@PathVariable long id){
        Recipe recipe = recipeService.getSingleRecipe(id);
        if(recipe != null){
            return ResponseEntity.ok(recipe);
        } else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/new")
    public ResponseEntity<Response> createRecipe(@RequestBody Recipe newRecipe){
        Response response = new Response();
        response.setId(recipeService.save(newRecipe).getId());
        return ResponseEntity.ok().body(response);
    }
}


class Response{
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
