package com.ebook.management.categoryservice.dto;

import jakarta.validation.constraints.NotBlank;


 public class CategoryDto {
  private Long id;

  @NotBlank(message = "Category name is required")
  private String name;
  private String description;
  private Long parentId;

  private boolean active;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
