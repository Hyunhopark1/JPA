package com.maven.springboot.myjpa.service.phonebook;

import com.maven.springboot.myjpa.model.category.ECategory;
import com.maven.springboot.myjpa.model.phonebook.IPhoneBook;

import java.util.List;

public interface IPhoneBookService<T> {
    T findById(Long id);
    List<T> getAllList();
    IPhoneBook insert(String name, ECategory category, String phoneNumber, String email) throws Exception;
    IPhoneBook insert(T phoneBook) throws Exception;
    boolean remove(Long id) throws Exception;
    IPhoneBook update(Long id, T phoneBook) throws Exception;
    List<T> getListFromName(String findName);
    List<T> getListFromCategory(ECategory category);

    List<T> getListFromPhoneNumber(String findPhone);
    List<T> getListFromEmail(String findEmail);
}
