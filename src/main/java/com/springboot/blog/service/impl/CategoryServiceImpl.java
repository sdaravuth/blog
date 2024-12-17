package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Category;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long id) {
        // TODO Auto-generated method stub
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("category", "category service Impl::", id));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        // TODO Auto-generated method stub
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                    .map((category) -> modelMapper.map(category, CategoryDto.class))
                    .collect(Collectors.toList());

    }

    @Override
    public CategoryDto updateCategory(long id, CategoryDto categoryDto) {
        // TODO Auto-generated method stub
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("category", "category service impl ", id));
        
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setId(id);

        return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
    }

    @Override
    public void deleteCatetory(long id) {
        // TODO Auto-generated method stub
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("category", "category service impl ", id));
        categoryRepository.delete(category);
    }

}
