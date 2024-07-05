package com.maven.springboot.myjpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneBookJpaRepository extends JpaRepository<PhoneBookEntity,Long> {
    List<PhoneBookEntity> findAllByNameContains(String name);
    List<PhoneBookEntity> findAllByPhoneNumberContains(String phone);
    List<PhoneBookEntity> findAllByEmailContains(String email);
    List<PhoneBookEntity> findAllByCategory(ECategory category);




}
