package com.maven.springboot.myjpa.model.category;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements ICategory {

    private Long id;
    private String name;

    @Override
    public String toString() {
        return String.format("ID : %6d, 이름 : %s", this.id, this.name);
    }
}
