package com.crave.crave.controller;

import com.crave.crave.dto.CategoryDTO;
import com.crave.crave.exception.InvalidCategoryException;
import com.crave.crave.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/recipe/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getCategory(@RequestParam String name,
                                         @RequestParam(defaultValue = "0") int pageNo,
                                         @RequestParam(defaultValue = "10") int pageSize,
                                         @RequestParam(defaultValue = "id") String sortBy){

        Optional<CategoryDTO> categoryDTo = categoryService.findByName(name, pageNo, pageSize, sortBy);
        if(categoryDTo.isPresent()){
            return ResponseEntity.ok(categoryDTo.get());
        } else
            return new ResponseEntity<>(new InvalidCategoryException("Invalid category", "", "Please select a valid category"), HttpStatus.NOT_FOUND);

    }
}
