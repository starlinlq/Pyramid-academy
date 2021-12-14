package com.crave.crave.service;

import com.crave.crave.dto.CategoryDTO;
import com.crave.crave.dto.RecipeDTO;
import com.crave.crave.model.Category;
import com.crave.crave.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Optional<Category> findById(long id) {
        return categoryRepo.findById(id);
    }

    @Override
    public Optional<CategoryDTO> findByName(String name, int pageNo, int pageSize, String sortBy) {
        Pageable paging  = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Category> request = categoryRepo.findByName(name, paging);

        if(request.hasContent()){
            Category category = request.getContent().get(0);
            CategoryDTO categoryDTO = new CategoryDTO(category.getName(),
                                                               category.getRecipes().stream()
                                                              .map(RecipeServiceImpl.RecipeMapper::toRecipeDTO)
                                                              .collect(Collectors.toList()));
            return Optional.of(categoryDTO);
        }

        return Optional.empty();

    }
}
