package com.blogapp.api.services;

import com.blogapp.api.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,int id);
    CategoryDto getCategoryById(int id);
    List<CategoryDto> getAllCategories();
    void deleteCategory(int id);
}
