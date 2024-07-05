package com.maven.springboot.myjpa.service;



import com.maven.springboot.myjpa.model.ECategory;
import com.maven.springboot.myjpa.model.IPhoneBook;
import com.maven.springboot.myjpa.model.PhoneBookEntity;
import com.maven.springboot.myjpa.repository.PhoneBookJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class PhoneBookServiceImpl implements IPhoneBookService<IPhoneBook> {

    @Autowired
    private PhoneBookJpaRepository phoneBookJpaRepository;




    @Override
    public IPhoneBook findById(Long id) {

        Optional<PhoneBookEntity> find = this.phoneBookJpaRepository.findById(id);
        return find.orElse(null);
    }



    @Override
    public List<IPhoneBook> getAllList() {
        List<IPhoneBook> list = new ArrayList<>();
        List<PhoneBookEntity> entities = this.phoneBookJpaRepository.findAll();
        for (PhoneBookEntity entity :entities ) {
            list.add((IPhoneBook) entity);
        }
        return list;
    }

    @Override
    public IPhoneBook insert(String name, ECategory category, String phoneNumber, String email) throws Exception {
        PhoneBookEntity phoneBook = PhoneBookEntity.builder()
                .id(0L)
                .name(name).category(category)
                .phoneNumber(phoneNumber).email(email).build();

        return this.phoneBookJpaRepository.saveAndFlush(phoneBook);
    }

    private boolean isValidInsert(IPhoneBook dto) {
        if (dto == null) {
            return false;
        } else if (dto.getName() == null || dto.getName().isEmpty()) {
            return false;
        }else if (dto.getCategory() == null) {
            return false;
        }
        return true;
    }
    @Override
    public IPhoneBook insert(IPhoneBook phoneBook) throws Exception {
        if (!this.isValidInsert(phoneBook)) {
            return null;
        }
        PhoneBookEntity entity = new PhoneBookEntity();
        entity.copyFields(phoneBook);
        IPhoneBook result = this.phoneBookJpaRepository.saveAndFlush(entity);
        return result;
    }

    @Override
    public boolean remove(Long id) {
        IPhoneBook find = this.findById(id);
        if ( find == null ) {
            return false;

        }
        PhoneBookEntity entity = new PhoneBookEntity();
        entity.copyFields(find);
        this.phoneBookJpaRepository.delete(entity);
        this.phoneBookJpaRepository.flush();
        return true;
    }

    // id를 받아서 객체1을찾고 객체2를 받아서 객체1을 객체2의값으로 수정한다.
    @Override
    public IPhoneBook update(Long id, IPhoneBook phoneBook) {
        IPhoneBook find = this.findById(id);
        if (find == null) {
            return null;
        }
        PhoneBookEntity entity = new PhoneBookEntity();
        find.copyFields(phoneBook);
        entity.copyFields(find);
        IPhoneBook result = this.phoneBookJpaRepository.saveAndFlush(entity);
        return result;
    }

    @Override
    public List<IPhoneBook> getListFromName(String findName) {

        if (findName == null || findName.isEmpty()) {
            return new ArrayList<>();
        }
        List<PhoneBookEntity> list = this.phoneBookJpaRepository.findAllByNameContains(findName);
        List<IPhoneBook> result = new ArrayList<>();
        for (PhoneBookEntity item : list) {
            result.add((IPhoneBook) item);
        }
        return result;
    }

    @Override
    public List<IPhoneBook> getListFromCategory(ECategory category) {

        if (category == null) {
            return new ArrayList<>();
        }
        List<PhoneBookEntity> list = this.phoneBookJpaRepository.findAllByCategory(category);
        List<IPhoneBook> result = list.stream()
                .map(x-> (IPhoneBook)x).toList();
        return result;
    }

    //여기부터 해야됨
    @Override
    public List<IPhoneBook> getListFromPhoneNumber(String findPhone) {

        if (findPhone == null) {
            return new ArrayList<>();
        }
        List<PhoneBookEntity> list = this.phoneBookJpaRepository.findAllByPhoneNumberContains(findPhone);
        List<IPhoneBook> result = list.stream()
                .map(y -> (IPhoneBook) y).toList();

        return result;
    }

    @Override
    public List<IPhoneBook> getListFromEmail(String findEmail) {

        if (findEmail == null) {
            return new ArrayList<>();
        }
        List<PhoneBookEntity> list = this.phoneBookJpaRepository.findAllByEmailContains(findEmail);
        List<IPhoneBook> result = list.stream()
                .map( z ->(IPhoneBook)z).toList();
        return result;
    }


}