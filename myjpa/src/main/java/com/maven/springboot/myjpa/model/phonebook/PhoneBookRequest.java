package com.maven.springboot.myjpa.model.phonebook;


import com.fasterxml.jackson.annotation.JsonIgnore;

import com.maven.springboot.myjpa.model.category.CategoryEntity;
import com.maven.springboot.myjpa.model.category.ICategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder

public class PhoneBookRequest implements IPhoneBook {
    @JsonIgnore
    private Long id;

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @NotBlank
    private CategoryEntity category;

    @NotBlank
    @Size(min = 0, max = 20)
    private String phoneNumber;

    @Size(min = 0, max = 200)
    private String email;

    @Override
    public void setCategory(ICategory category) {
        if (category == null) {
            return;
        }
        //아래 코드는 매개변수로 CategoryDto를 받게되면 형변환이 안되 런타임에러 발생할 수 있어 안좋은 코드이다.
        //        this.category = (CategoryEntity) category;
        //아래코드로하게되면  런타임에러는 절대 발생하지않는다 -> 좋은코드
        CategoryEntity entity = new CategoryEntity();
        entity.copyFields(category);
        this.category = entity;
    }
}
