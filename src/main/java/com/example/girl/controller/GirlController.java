package com.example.girl.controller;


import com.example.girl.domain.Girl;
import com.example.girl.repository.GirlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * 表单验证需要注入以下依赖
 * <dependency>
 * <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-starter-validation</artifactId>
 * </dependency>
 */

@RestController
public class GirlController {

    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

    private GirlRepository girlRepository;

    @Autowired
    public void setGirlRepository(GirlRepository girlRepository) {
        this.girlRepository = girlRepository;
    }

    /*
       查找所有女生
    */
    @GetMapping("/girls")
    public List<Girl> girlList() {
        logger.info("girllist");
        return girlRepository.findAll();
    }

    /*
        添加一个女生
     */
    @PostMapping("/girl")
    public Girl girlAdd(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }

        girl.setName(girl.getName());
        girl.setAge(girl.getAge());
        girl.setSalary(girl.getSalary());
        return girlRepository.save(girl);
    }

    /*
        根据id查询女生
     */
    @GetMapping("/girl/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id) {
        return girlRepository.findById(id).orElse(null);
    }

    /*
        更新
     */
    @PutMapping("/girl/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,@Valid Girl girl,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }

        Optional<Girl> optional = girlRepository.findById(id);
        if (optional.isPresent()) {
            girl.setName(girl.getName());
            girl.setAge(girl.getAge());
            girl.setSalary(girl.getSalary());
            return girlRepository.save(girl);
        }
        return null;
    }


}
