package com.crave.crave.service;

import com.crave.crave.dto.CategoryDTO;
import com.crave.crave.model.Category;

import java.util.Optional;

public interface CategoryService {
    Category save(Category category);
    Optional<Category> findById(long id);
    Optional<CategoryDTO> findByName(String name, int pageNo, int pageSize, String sortBy);
}
