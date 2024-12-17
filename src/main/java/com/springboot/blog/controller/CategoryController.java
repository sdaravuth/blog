package com.springboot.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.service.CategoryService;





@RestController
@RequestMapping("/api/categories")
public class CategoryController {
 
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping()
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        //TODO: process POST request
        return new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable( name = "id") Long id) {
        return ResponseEntity.ok(categoryService.getCategory(id));

    }

    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCateogry(@PathVariable long id, @RequestBody CategoryDto categoryDto) {
        //TODO: process PUT request
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteCateogry(@PathVariable long id) {
        //TODO: process PUT request
        categoryService.deleteCatetory(id);
        return ResponseEntity.ok("deleted sucessfully");
    }
    
    
}
