package com.ebook.management.categoryservice.repository;

import com.ebook.management.categoryservice.domain.Category;
import java.util.List;

import com.ebook.management.categoryservice.dto.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  List<Category> findByParentIdAndDeletedFalse(Long parentId);
  List<Category> findByDeletedFalse();

  @Query("SELECT c FROM Category c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) "
      + "AND c.active = true AND c.deleted = false")
  List<Category> findActiveCategoriesByName(@Param("name") String name);
}
