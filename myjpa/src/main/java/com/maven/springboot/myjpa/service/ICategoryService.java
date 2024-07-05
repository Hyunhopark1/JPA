package com.maven.springboot.myjpa.service;

import com.maven.springboot.myjpa.model.ICategory;
import com.maven.springboot.myjpa.model.IPhoneBook;

import java.util.List;


public interface ICategoryService<T> {
    T findById(Long id);
    List<T> getAllList();
    ICategory insert(T category) throws Exception;
    boolean remove(Long id) throws Exception;
    ICategory update(Long id, T category) throws Exception;
    List<T> getListFromName(String findName);
}
