package com.blogapp.api.controllers;

import com.blogapp.api.payloads.ApiResponse;
import com.blogapp.api.payloads.CategoryDto;
import com.blogapp.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/api/categories")   //add new user
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createCatDto=this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createCatDto, HttpStatus.CREATED);
    }
    @PutMapping("/api/categories/{id}")  //update user
    public ResponseEntity<String> updateCategory(@Valid @PathVariable int id, @RequestBody CategoryDto categoryDto){
        this.categoryService.updateCategory(categoryDto,id);
        return ResponseEntity.ok ("user updated");
    }
    @DeleteMapping("/api/categories/{id}")   //delete user
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int id){
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfrully",true),HttpStatus.OK);
    }
    @GetMapping("/api/categories")   //get all users
    public ResponseEntity<List<CategoryDto>> getAllcategories(){
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }
    @GetMapping("/api/categories/{id}")   //get user by id
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int id){
        return ResponseEntity.ok(this.categoryService.getCategoryById(id));
    }
}
