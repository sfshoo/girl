package com.example.girl.controller;


import com.example.girl.domain.Girl;
import com.example.girl.domain.Result;
import com.example.girl.enums.ResultEnum;
import com.example.girl.repository.GirlRepository;
import com.example.girl.service.GirlService;
import com.example.girl.utils.ResultUtil;
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

    private GirlService girlService;

    @Autowired
    public void setGirlService(GirlService girlService) {
        this.girlService = girlService;
    }

    @Autowired
    public void setGirlRepository(GirlRepository girlRepository) {
        this.girlRepository = girlRepository;
    }

    /*
       查找所有女生
    */
    @GetMapping("/girls")
    public Result<Girl> girlList() {
        logger.info("girllist");
        return ResultUtil.success(ResultEnum.SUCCESS, girlRepository.findAll());
    }

    /*
        添加一个女生
     */
    @PostMapping("/girl")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(ResultEnum.UNKNOW_ERROR);
        }

        return ResultUtil.success(ResultEnum.SUCCESS, girlRepository.save(girl));
    }

    /*
        根据id查询女生
     */
    @GetMapping("/girl/{id}")
    public Result girlFindOne(@PathVariable("id") Integer id) {
        return ResultUtil.success(ResultEnum.SUCCESS, girlRepository.findById(id).orElse(null));
    }

    /*
        更新
     */
    @PutMapping("/girl/{id}")
    public Result girlUpdate(@PathVariable("id") Integer id, @Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(ResultEnum.FIELD_ERROR, bindingResult.getFieldError().getDefaultMessage());
        }

        Optional<Girl> optional = girlRepository.findById(id);
        if (optional.isPresent()) {
            return ResultUtil.success(ResultEnum.SUCCESS, girlRepository.save(girl));
        }
        return null;
    }

    /*
        删除
     */
    @DeleteMapping("/girl/{id}")
    public Result<Girl> girlDelete(@PathVariable Integer id) {
        if (girlRepository.existsById(id)) {
            girlRepository.deleteById(id);
            return ResultUtil.success(ResultEnum.SUCCESS);
        } else {
            return ResultUtil.error(ResultEnum.OBJECT_ERROR);
        }

    }

    /*
        通过年龄查询女生列表
     */
    @GetMapping("/girls/{age}")
    public Result girlListByAge(@PathVariable Integer age) {
        return ResultUtil.success(ResultEnum.SUCCESS, girlRepository.findByAge(age));
    }

    /*
        判断年龄
     */
    @GetMapping("/girl/getAge/{id}")
    public void getAge(@PathVariable Integer id) throws Exception {
        girlService.getAge(id);
    }

}
