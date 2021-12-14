package com.crave.crave.repository;

import com.crave.crave.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecipeRepo extends JpaRepository<Recipe, Long> {
    Page<Recipe> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Recipe> findByUserId(long id, Pageable pageable);
}
