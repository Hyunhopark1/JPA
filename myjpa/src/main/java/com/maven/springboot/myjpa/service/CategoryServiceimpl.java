package com.maven.springboot.myjpa.service;

import com.maven.springboot.myjpa.model.CategoryEntity;
import com.maven.springboot.myjpa.model.ICategory;
import com.maven.springboot.myjpa.model.IPhoneBook;
import com.maven.springboot.myjpa.model.PhoneBookEntity;
import com.maven.springboot.myjpa.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceimpl implements ICategoryService<ICategory> {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public ICategory findById(Long id) {

        Optional<CategoryEntity> find = categoryRepository.findById(id);
        return find.orElse(null);
    }

    @Override
    public List<ICategory> getAllList() {
        List<ICategory> list = new ArrayList<>();
        List<CategoryEntity> entityList = categoryRepository.findAll();
        for (CategoryEntity items : entityList) {
            list.add((ICategory) items);
        }
        return list;
    }

    @Override
    public ICategory insert(ICategory category) throws Exception {
        if (category == null) {
            return null;
        }
        CategoryEntity entity = new CategoryEntity();
        entity.copyFields(category);
        ICategory result = categoryRepository.saveAndFlush(entity);
        return result;
    }

    @Override
    public boolean remove(Long id) throws Exception {
        ICategory find = this.findById(id);
        if (find == null) {
            return false;
        }
        CategoryEntity entity = new CategoryEntity();
        entity.copyFields(find);
        categoryRepository.delete(entity);
        categoryRepository.flush();
        return true;
    }

    @Override
    public ICategory update(Long id, ICategory category) throws Exception {
        ICategory find = this.findById(id);
        if (find == null) {
            return null;
        }
        CategoryEntity entity = new CategoryEntity();
        find.copyFields(category);
        entity.copyFields(find);

        ICategory result = categoryRepository.saveAndFlush(entity);
        return result;
    }

    @Override
    public List<ICategory> getListFromName(String findName) {
        if (findName == null || findName.isEmpty()) {
            return new ArrayList<>();
        }
        List<CategoryEntity> list = this.categoryRepository.findAllByNameContains(findName);
        List<ICategory> result = new ArrayList<>();
        for (CategoryEntity item : list) {
            result.add((ICategory) item);
        }
        return result;
    }
}
