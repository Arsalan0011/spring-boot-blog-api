package com.blogapp.api.services.impl;

import com.blogapp.api.entities.Category;
import com.blogapp.api.exceptions.ResourceNotFoundException;
import com.blogapp.api.payloads.CategoryDto;
import com.blogapp.api.repositries.CategoryRepo;
import com.blogapp.api.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category= this.dtotoCategory(categoryDto);
        return this.categorytoDto(this.categoryRepo.save(category));

    }
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int id) {
        Category category=categoryRepo.findById(id).orElseThrow(() ->new ResourceNotFoundException("Category","cat id",id));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDesc(categoryDto.getCategoryDesc());
        return this.categorytoDto(this.categoryRepo.save(category));

    }
    @Override
    public CategoryDto getCategoryById(int id) {
         Category category =this.categoryRepo.findById(id).orElseThrow(( )-> new ResourceNotFoundException("Category","cat id",id));
         return this.categorytoDto(category);
    }
    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> allCategories= this.categoryRepo.findAll();
        List<CategoryDto> allCategoriesLsit=allCategories.stream().map(category -> this.categorytoDto(category)).
                collect(Collectors.toList());
        return allCategoriesLsit;
    }
    @Override
    public void deleteCategory(int id) {
        Category category =this.categoryRepo.findById(id).orElseThrow(( )-> new ResourceNotFoundException("Category","cat id",id));
        this.categoryRepo.delete(category);

    }
    private Category dtotoCategory(CategoryDto categoryDto){
        Category category=modelMapper.map(categoryDto,Category.class);
        return category;
    }
    private CategoryDto categorytoDto(Category category){
        CategoryDto categoryDto=modelMapper.map(category,CategoryDto.class);
        return categoryDto;
    }
}
