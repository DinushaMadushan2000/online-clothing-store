package com.zosh.service;

import com.zosh.model.Category;

public interface CategoryService {

    public Category createCategory(String name,Long userId);

    public Category findCategoryById(Long id)throws Exception;
}
