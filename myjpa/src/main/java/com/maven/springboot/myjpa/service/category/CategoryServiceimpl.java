package com.maven.springboot.myjpa.service.category;

import com.maven.springboot.myjpa.model.category.CategoryEntity;
import com.maven.springboot.myjpa.model.category.ICategory;
import com.maven.springboot.myjpa.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceimpl implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public ICategory findById(Long id) {

        Optional<CategoryEntity> find = categoryRepository.findById(id);
        return find.orElse(null);
    }

    @Override
    public ICategory findByName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        Optional<CategoryEntity> find = categoryRepository.findByName(name);
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

    private List<ICategory> getICategoryList(List<CategoryEntity> list) {
        if ( list == null || list.size() <= 0 ) {
            return new ArrayList<>();
        }
        // input : [CategoryEntity|CategoryEntity|CategoryEntity|CategoryEntity|CategoryEntity]
//        List<ICategory> result = new ArrayList<>();
//        for( CategoryEntity entity : list ) {
//            result.add( (ICategory)entity );
//        }
        List<ICategory> result = list.stream()
                .map(entity -> (ICategory)entity)
                .toList();
        // return : [ICategory|ICategory|ICategory|ICategory|ICategory]
        return result;
    }

    @Override
    public ICategory insert(ICategory category) throws Exception {
        if (category == null) {
            return null;
        }
        CategoryEntity entity = new CategoryEntity();
        entity.copyFields(category);
        entity.setId(0L);
        ICategory result = this.categoryRepository.saveAndFlush(entity);
        return result;
    }
    private boolean isValidInsert(ICategory category) {
        if ( category == null ) {
            return false;
        } else if ( category.getName() == null || category.getName().isEmpty() ) {
            return false;
        }
        return true;
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
    public List<ICategory> findAllByNameContains(String name) {
        if ( name == null || name.isEmpty() ) {
            //return List.of();
            return new ArrayList<>();
        }
        List<ICategory> list = this.getICategoryList(
                this.categoryRepository.findAllByNameContains(name)
        );
        return list;
    }
}
