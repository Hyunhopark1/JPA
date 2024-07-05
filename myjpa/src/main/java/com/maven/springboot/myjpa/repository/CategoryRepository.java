package com.maven.springboot.myjpa.repository;

import com.maven.springboot.myjpa.model.CategoryEntity;
import com.maven.springboot.myjpa.model.ICategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByNameContains(String name);
}
