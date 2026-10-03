package com.ebook.management.categoryservice.mapper;

import com.ebook.management.categoryservice.domain.Category;
import com.ebook.management.categoryservice.dto.CategoryDto;

public class CategoryMapper {

  public static CategoryDto toDto(Category category) {
    if (category == null) {
      return null;
    }
    CategoryDto dto = new CategoryDto();
    dto.setId(category.getId());
    dto.setName(category.getName());
    dto.setDescription(category.getDescription());
    dto.setParentId(category.getParentId());
    dto.setActive(category.isActive());
    return dto;
  }

  public static Category toEntity(CategoryDto dto) {
    if (dto == null) {
      return null;
    }
    Category category = new Category();
    category.setId(dto.getId());
    category.setName(dto.getName());
    category.setDescription(dto.getDescription());
    category.setParentId(dto.getParentId());
    category.setActive(dto.isActive());
    return category;
  }
}
