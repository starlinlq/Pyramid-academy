package com.crave.crave.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/recipe")
public class RecipeController {


    @GetMapping("/create")
    public ResponseEntity<?> createRecipe(){
        return ResponseEntity.ok("Welcome");
    }

}
