package com.zosh.service;

import com.zosh.model.Category;
import com.zosh.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category createCategory(String name, Long userId) {
        Category category=new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> optionalCategory=categoryRepository.findById(id);

        if (optionalCategory.isEmpty()){
            throw new Exception("category not found");
        }
        return optionalCategory.get();
    }
}
