package com.maven.springboot.myjpa.model.category;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class CategoryEntity implements ICategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 8)
    private String name;

    @Override
    public String toString() {
        return String.format("ID : %6d, 이름 : %s", this.id, this.name);
    }
}
