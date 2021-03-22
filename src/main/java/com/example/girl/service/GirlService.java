package com.example.girl.service;

import com.example.girl.domain.Girl;
import com.example.girl.enums.ResultEnum;
import com.example.girl.exception.GirlException;
import com.example.girl.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class GirlService {
    private GirlRepository girlRepository;

    @Autowired
    public void setGirlRepository(GirlRepository girlRepository) {
        this.girlRepository = girlRepository;
    }

    @Transactional
    public void insertTwo() {
        Girl girlA = new Girl();
        girlA.setName("A");
        girlA.setAge(18);
        girlA.setSalary(new BigDecimal("6000"));
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setName("B");
        girlB.setAge(36);
        girlB.setSalary(new BigDecimal("30000"));
        girlRepository.save(girlB);
    }

    public void getAge(Integer id) throws Exception {
        Girl girl = girlRepository.findById(id).get();
        Integer age = girl.getAge();

        if (age < 10) {
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        } else if (age > 10 && age < 16) {
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
    }

}
