package com.maven.springboot.myjpa.controller;




import com.maven.springboot.myjpa.model.category.CategoryDto;
import com.maven.springboot.myjpa.model.category.CategoryEntity;
import com.maven.springboot.myjpa.model.category.ICategory;
import com.maven.springboot.myjpa.model.phonebook.IPhoneBook;
import com.maven.springboot.myjpa.model.phonebook.PhoneBookRequest;
import com.maven.springboot.myjpa.service.category.ICategoryService;
import com.maven.springboot.myjpa.service.phonebook.IPhoneBookService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Builder
@Slf4j
@RestController
@RequestMapping("/pb")
public class PhoneBookController {

    private static final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

    @Autowired
    private IPhoneBookService<IPhoneBook> phoneBookService;

    @Autowired
    private ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<IPhoneBook> insertPB(@RequestBody PhoneBookRequest dto) {
        try {
            if (dto == null) {
                return ResponseEntity.badRequest().build();
            }
            IPhoneBook result = this.phoneBookService.insert(dto);
            if (result == null) {
                return ResponseEntity.badRequest().build();
            }
            if (result.getCategory() != null) {
                ICategory find = this.categoryService.findById(result.getCategory().getId());
                result.setCategory(find);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<IPhoneBook>> getAll() {
        try {
            List<IPhoneBook> result = this.phoneBookService.getAllList();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }


    // Long 처럼 박스형 클래스는 null이 될수있어서 검사해야한다. 하지만 long 처럼 기본변수타입은 null이 될수없다
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.badRequest().build();
            }
            Boolean result = this.phoneBookService.remove(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    //RequestBody 부분에 인터페이스가 올 수 없다.
    @PatchMapping("/{id}")
    public ResponseEntity<IPhoneBook> update(@PathVariable Long id, @RequestBody PhoneBookRequest dto) {
        try{
            if (id == null || dto == null) {
                return ResponseEntity.badRequest().build();
            }
            IPhoneBook result = this.phoneBookService.update(id,dto);
            if (result == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<IPhoneBook> findById(@PathVariable Long id) {
        try{
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest().build();
            }
            IPhoneBook result = this.phoneBookService.findById(id);
            if (result == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<List<IPhoneBook>> findAllByNameContains(@PathVariable String name) {
        try{
            if (name == null || name.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            List<IPhoneBook> result = this.phoneBookService.getListFromName(name);
            if (result == null || result.size() <= 0) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<IPhoneBook>> findAllByPhoneNumberContains(@PathVariable String phone) {
        try{
            if (phone == null || phone.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            List<IPhoneBook> result = this.phoneBookService.getListFromPhoneNumber(phone);
            if (result == null || result.size() <= 0) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<List<IPhoneBook>> findAllByEmailContains(@PathVariable String email) {
        try{
            if (email == null || email.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            List<IPhoneBook> result = this.phoneBookService.getListFromEmail(email);
            if (result == null || result.size() <= 0) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ct/{category}")
    public ResponseEntity<List<IPhoneBook>> findAllByCategory(@PathVariable Integer category) {
        try{
            if (category == null) {
                return ResponseEntity.badRequest().build();
            }
            CategoryEntity categoryEntity = CategoryEntity.builder()
                    .id(Long.parseLong(category.toString()))
                    .build();
            List<IPhoneBook> result = this.phoneBookService.getListFromCategory(categoryEntity);
            if (result == null || result.size() <= 0) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }
}
