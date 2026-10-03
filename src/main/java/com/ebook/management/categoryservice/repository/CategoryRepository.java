package com.ebook.management.categoryservice.repository;

import com.ebook.management.categoryservice.domain.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  List<Category> findByParentIdAndDeletedFalse(Long parentId);
  List<Category> findByDeletedFalse();
}
