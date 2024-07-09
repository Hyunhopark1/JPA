package com.maven.springboot.myjpa.service.category;

import com.maven.springboot.myjpa.model.category.ICategory;

import java.util.List;


public interface ICategoryService {
    ICategory findById(Long id);
    ICategory findByName(String name);
    List<ICategory> getAllList();
    ICategory insert(ICategory category) throws Exception;
    boolean remove(Long id) throws Exception;
    ICategory update(Long id, ICategory category) throws Exception;
    List<ICategory> findAllByNameContains(String name);
}
