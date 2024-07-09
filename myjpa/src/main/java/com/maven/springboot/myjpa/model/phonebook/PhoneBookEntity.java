package com.maven.springboot.myjpa.model.phonebook;


import com.maven.springboot.myjpa.model.category.CategoryEntity;
import com.maven.springboot.myjpa.model.category.ICategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phonebook")
public class PhoneBookEntity implements IPhoneBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 50, unique = true)
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @NotNull
    @Column(length = 50)
    private String phoneNumber;

    @Column(length = 50)
    private String email;

    @Override
    public String toString() {
        return String.format("ID : %6d, 이름 : %s,분류: %s, 번호: %s, 이메일: %s",
                this.id, this.name, this.category, this.phoneNumber, this.email);
    }

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
