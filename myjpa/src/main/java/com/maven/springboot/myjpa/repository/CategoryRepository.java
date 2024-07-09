package com.maven.springboot.myjpa.repository;

import com.maven.springboot.myjpa.model.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
//CategoryEntity 클래스로 DB테이블을 만듦, id는 Long타입
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(String name);
    List<CategoryEntity> findAllByNameContains(String name);
}
