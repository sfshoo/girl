package com.example.girl.controller;

import com.example.girl.config.GirlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//    @Value("${girl.age}")
//    private Integer age;

    private GirlConfig girlConfig;

    @Autowired
    public void setGirlConfig(GirlConfig girlConfig) {
        this.girlConfig = girlConfig;
    }

    @GetMapping("/hello")
    public String hello() {
        return "age:" + girlConfig.getAge();
    }
}
