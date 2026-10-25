package com.ebook.management.categoryservice.service;

import com.ebook.management.categoryservice.domain.Category;
import com.ebook.management.categoryservice.dto.CategoryDto;
import com.ebook.management.categoryservice.exception.ResourceNotFoundException;
import com.ebook.management.categoryservice.mapper.CategoryMapper;
import com.ebook.management.categoryservice.repository.CategoryRepository;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

  private final CategoryRepository repository;

  public CategoryService(CategoryRepository repository) {
    this.repository = repository;
  }

  @Transactional(readOnly = true)
  public List<CategoryDto> getAllCategories() {
    return repository.findByDeletedFalse().stream()
        .map(CategoryMapper::toDto)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public CategoryDto getCategoryById(Long id) {
    return repository.findById(id)
        .filter(category -> !category.isDeleted())
        .map(CategoryMapper::toDto)
        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
  }

  @Transactional
  public CategoryDto createCategory(CategoryDto dto) {
    Category category = CategoryMapper.toEntity(dto);
    category.setDeleted(false);
    category.setCreatedAt(LocalDateTime.now());
    category.setUpdatedAt(LocalDateTime.now());
    return CategoryMapper.toDto(repository.save(category));
  }

  @Transactional
  public CategoryDto updateCategory(Long id, CategoryDto dto) {
    Category category = repository.findById(id)
        .filter(c -> !c.isDeleted())
        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    category.setName(dto.getName());
    category.setDescription(dto.getDescription());
    category.setParentId(dto.getParentId());
    category.setActive(dto.isActive());
    category.setUpdatedAt(LocalDateTime.now());
    return CategoryMapper.toDto(repository.save(category));
  }

  @Transactional
  public void deleteCategory(Long id) {
    Category category = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    category.setDeleted(true);
    category.setUpdatedAt(LocalDateTime.now());
    repository.save(category);
  }
  @Transactional(readOnly = true)
  public List<CategoryDto> getActiveCategories(String name) {
    return repository.findActiveCategoriesByName(name).stream()
        .map(CategoryMapper::toDto)
        .collect(Collectors.toList());
  }
}
