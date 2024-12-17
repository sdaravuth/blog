package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.payload.CategoryDto;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto getCategory(Long id);
    List<CategoryDto> getAllCategories();
    CategoryDto updateCategory(long id, CategoryDto categoryDto);
    void deleteCatetory(long id);
}
