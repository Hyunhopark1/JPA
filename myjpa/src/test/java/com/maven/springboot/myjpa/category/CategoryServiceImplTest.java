package com.maven.springboot.myjpa.category;


import com.maven.springboot.myjpa.model.category.CategoryDto;
import com.maven.springboot.myjpa.model.category.ICategory;
import com.maven.springboot.myjpa.service.category.ICategoryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryServiceImplTest {

    @Autowired
    private ICategoryService categoryService;

    private void AssertFields(ICategory left, ICategory right) {
        assertThat(left).isNotNull();
        assertThat(right).isNotNull();
        assertThat(left.getId()).isEqualTo(right.getId());
        assertThat(left.getName()).isEqualTo(right.getName());
    }

    @Test
    @Order(1)
    public void CategoryInsertTest() throws Exception {
        CategoryDto insert = CategoryDto.builder()
                .name("123456789012345678901").build();
        Throwable exception = assertThrows(Exception.class, () -> {
            categoryService.insert(insert);
        });
        System.out.println(exception.toString());


        CategoryDto insert2 = CategoryDto.builder().build();
        ICategory resultInsert2 = categoryService.insert(insert2);
        assertThat(resultInsert2).isNull();


        CategoryDto insert3 = CategoryDto.builder()
                .name("AAAAAAA").build();
        ICategory resultInsert = categoryService.insert(insert3);
        assertThat(resultInsert).isNotNull();
        assertThat(resultInsert.getId()).isGreaterThan(0L);
        assertThat(resultInsert.getName()).isEqualTo("AAAAAAA");


    }


    @Test
    @Order(2)
    public void CategoryFindTest() {
        ICategory find1 = this.categoryService.findById(0L);
        ICategory find2ICategory = this.categoryService.findByName("AAAAAAA");
        ICategory find3ICategory = this.categoryService.findById(find2ICategory.getId());
        this.AssertFields(find3ICategory, find2ICategory);
    }

    @Test
    @Order(3)
    public void CategoryUpdateTest() throws Exception {
        ICategory find2ICategory = this.categoryService.findByName("AAAAAAA");
        find2ICategory.setName("ABCDEFGH");
        ICategory save = this.categoryService.update(find2ICategory.getId(), find2ICategory);
        this.AssertFields(save, find2ICategory);
        ICategory find3ICategory = this.categoryService.findById(find2ICategory.getId());
        this.AssertFields(find3ICategory, find2ICategory);
    }

    @Test
    @Order(4)
    public void CategoryDeleteTest() throws Exception {
        ICategory find2ICategory = this.categoryService.findByName("ABCDEFGH");
        assertThat(find2ICategory).isNotNull();
        this.categoryService.remove(find2ICategory.getId());
        ICategory find3ICategory = this.categoryService.findById(find2ICategory.getId());
        assertThat(find3ICategory).isNull();
    }
}
