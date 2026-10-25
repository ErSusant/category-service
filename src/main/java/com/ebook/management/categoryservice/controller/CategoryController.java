package com.ebook.management.categoryservice.controller;

import com.ebook.management.categoryservice.dto.ApiResponse;
import com.ebook.management.categoryservice.dto.CategoryDto;
import com.ebook.management.categoryservice.service.CategoryService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/categories")
@CrossOrigin("*")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<CategoryDto>>> getCategories() {
    try {
      List<CategoryDto> categories = categoryService.getAllCategories();
      log.info("Fetched all categories successfully. Total records: {}",
          categories != null ? categories.size() : 0);
      return ResponseEntity.ok(new ApiResponse<>(true, "Categories fetched successfully", categories));
    } catch (Exception e) {
      log.error("Error fetching all categories", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ApiResponse<>(false, "Failed to fetch categories: " + e.getMessage(), null));
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<CategoryDto>> getCategory(@PathVariable("id") Long id) {
    try {
      CategoryDto category = categoryService.getCategoryById(id);
      log.info("Fetched category with id {} successfully", id);
      return ResponseEntity.ok(new ApiResponse<>(true, "Category fetched successfully", category));
    } catch (Exception e) {
      log.error("Error fetching category with id {}", id, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ApiResponse<>(false, "Failed to fetch category: " + e.getMessage(), null));
    }
  }

  @PostMapping
  public ResponseEntity<ApiResponse<CategoryDto>> createCategory(@Valid @RequestBody CategoryDto dto) {
    try {
      CategoryDto createdCategory = categoryService.createCategory(dto);
      log.info("Category created successfully with id {}", createdCategory != null ? createdCategory.getId() : null);
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(new ApiResponse<>(true, "Category created successfully", createdCategory));
    } catch (Exception e) {
      log.error("Error creating category: {}", dto, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ApiResponse<>(false, "Failed to create category: " + e.getMessage(), null));
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<CategoryDto>> updateCategory(@PathVariable("id") Long id,
      @Valid @RequestBody CategoryDto dto) {
    try {
      CategoryDto updatedCategory = categoryService.updateCategory(id, dto);
      log.info("Category updated successfully with id {}", id);
      return ResponseEntity.ok(new ApiResponse<>(true, "Category updated successfully", updatedCategory));
    } catch (Exception e) {
      log.error("Error updating category with id {}", id, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ApiResponse<>(false, "Failed to update category: " + e.getMessage(), null));
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable("id") Long id) {
    try {
      categoryService.deleteCategory(id);
      log.info("Category deleted successfully with id {}", id);
      return ResponseEntity.ok(new ApiResponse<>(true, "Category deleted successfully", null));
    } catch (Exception e) {
      log.error("Error deleting category with id {}", id, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ApiResponse<>(false, "Failed to delete category: " + e.getMessage(), null));
    }
  }

  @GetMapping("/search")
  public ResponseEntity<ApiResponse<List<CategoryDto>>> searchCategories(
      @RequestParam("name") String name) {
    try {
      List<CategoryDto> categories = categoryService.getActiveCategories(name);
      log.info("Fetched active categories with name {} successfully. Total records: {}",
          name, categories != null ? categories.size() : 0);
      return ResponseEntity.ok(new ApiResponse<>(true, "Categories fetched successfully", categories));
    } catch (Exception e) {
      log.error("Error fetching active categories with name {}", name, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ApiResponse<>(false, "Failed to fetch categories: " + e.getMessage(), null));
    }
  }
}
