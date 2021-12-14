package com.crave.crave.controller;

import com.crave.crave.dto.RecipeDTO;
import com.crave.crave.exception.InvalidTokenException;
import com.crave.crave.exception.RecipeNotFoundException;
import com.crave.crave.model.Recipe;
import com.crave.crave.model.User;
import com.crave.crave.service.RecipeService;
import com.crave.crave.service.UserService;
import com.crave.crave.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/recipe")
public class RecipeController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public ResponseEntity<RecipeDTO> createRecipe(@Valid @RequestBody Recipe recipe, @RequestHeader("Authorization") String value){
        String username = getUserNameFromToken(value);
        Optional<User> user = userService.findByUsername(username);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("Invalid username");
        }

       return  new ResponseEntity<>(recipeService.saveRecipe(recipe, user.get()), HttpStatus.CREATED);

    }

    @GetMapping("/user")
    public ResponseEntity getUserRecipe(@RequestHeader("Authorization") String auth,
                                        @RequestParam(defaultValue = "0") int pageNo,
                                        @RequestParam(defaultValue = "10") int pageSize){

        String username = getUserNameFromToken(auth);
        long userId = userService.getUserIdByUsername(username);
        List<RecipeDTO> userRecipes = recipeService.findByUserId(userId, pageNo, pageSize);
        return ResponseEntity.ok(userRecipes);
    }

    @GetMapping("/search/")
    public ResponseEntity<?> searchByTitle(@RequestParam(defaultValue = "") String query,
                                           @RequestParam(defaultValue = "10") int pageSize,
                                           @RequestParam(defaultValue = "0") int pageNo){
        return ResponseEntity.ok(recipeService.findByTitleContaining(query, pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getSingleRecipe(@PathVariable long id) throws RecipeNotFoundException{
        Optional<RecipeDTO> recipe = recipeService.findById(id);

        if(recipe.isEmpty()){
            throw new RecipeNotFoundException();
        }

        return new ResponseEntity<>(recipe.get(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RecipeDTO>> getAllRecipes(@RequestParam(defaultValue = "0") int pageNo,
                                                         @RequestParam(defaultValue = "10") int pageSize,
                                                         @RequestParam(defaultValue = "id") String sortBy){
        return ResponseEntity.ok(recipeService.getAll(pageNo, pageSize, sortBy));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable long id, @RequestHeader("Authorization") String value){
        String username = getUserNameFromToken(value);
        Optional<User> user = userService.findByUsername(username);
        Optional<RecipeDTO> recipe = recipeService.findById(id);

        if(user.isPresent() && recipe.isPresent()){
            if(username.equals(recipe.get().getUserId())){
                recipeService.removeById(id);
                return ResponseEntity.ok().build();
            }
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Recipe> updateRecipe(@Valid @RequestBody Recipe recipe, @RequestHeader("Authorization") String value){

        if(recipe != null && recipe.getId() > 0){
            return ResponseEntity.ok(recipeService.update(recipe));
        } else
            throw new RecipeNotFoundException();

    }


    private String getUserNameFromToken(String t) throws InvalidTokenException {
        if(t != null && t.startsWith("Bearer")){
           return  jwtTokenUtil.getUsernameFromToken(t.split(" ")[1]);
        }
        throw new InvalidTokenException();
    }

}
