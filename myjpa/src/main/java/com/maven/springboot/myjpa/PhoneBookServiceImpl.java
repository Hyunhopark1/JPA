package com.maven.springboot.myjpa;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        for (PhoneBookEntity entity : this.phoneBookJpaRepository.findAll()) {
            list.add((IPhoneBook) entity);
        }
        return list;
    }

    @Override
    public IPhoneBook insert(String name, String category, String phoneNumber, String email) throws Exception {
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
        }else if (dto.getCategory() == null || dto.getCategory().isEmpty()) {
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
        if ( find != null ) {
            PhoneBookEntity entity = new PhoneBookEntity();
            entity.copyFields(find);
            this.phoneBookJpaRepository.delete(entity);
            this.phoneBookJpaRepository.flush();
            return true;
        }
        return false;
    }

    // id를 받아서 객체1을찾고 객체2를 받아서 객체1을 객체2의값으로 수정한다.
    @Override
    public IPhoneBook update(Long id, IPhoneBook phoneBook) {
        IPhoneBook find = this.findById(id);
        if (find != null) {
            PhoneBookEntity entity = new PhoneBookEntity();
            find.copyFields(phoneBook);
//            find.setName(phoneBook.getName());
//            find.setCategory(phoneBook.getCategory());
//            find.setEmail(phoneBook.getEmail());
//            find.setPhoneNumber(phoneBook.getPhoneNumber());
            entity.copyFields(find);
            IPhoneBook result = this.phoneBookJpaRepository.saveAndFlush(entity);
            return result;
        }
        return null;
    }

    @Override
    public List<IPhoneBook> getListFromName(String findName) {

        if (findName == null || findName.isEmpty()) {
            return new ArrayList<>();
        }

        return new ArrayList<>();
    }

    @Override
    public List<IPhoneBook> getListFromGroup(ECategory category) {

        if (category == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>();
    }

    @Override
    public List<IPhoneBook> getListFromPhoneNumber(String findPhone) {

        if (findPhone == null || findPhone.isEmpty()) {
            return new ArrayList<>();
        }

        return new ArrayList<>();
    }

    @Override
    public List<IPhoneBook> getListFromEmail(String findEmail) {
//        List<IPhoneBook> findArr = new ArrayList<>();
//        for ( IPhoneBook phoneBook : this.list ) {
//            if (phoneBook.getEmail().contains(findEmail)) {
//                findArr.add(phoneBook);
//            }
//        }
        if (findEmail == null || findEmail.isEmpty()) {
            return new ArrayList<>();
        }

        return new ArrayList<>();
    }


}
