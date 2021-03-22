package com.example.girl.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 先注入依赖
 * <dependency>
 * 			<groupId>org.springframework.boot</groupId>
 * 			<artifactId>spring-boot-starter-aop</artifactId>
 * </dependency>
 * 再建立文件
 *
 * 使用JOINPOINT类需要注入依赖
 * <dependency>
 *     <groupId>org.aspectj</groupId>
 *     <artifactId>aspectjweaver</artifactId>
 * </dependency>
 */

@Aspect
@Component
public class HttpAspect {

    //Logger使用org.slf4j.Logger
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.example.girl.controller.GirlController.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        // 记录http请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // url
        logger.info("url={}", request.getRequestURL());
        // method
        logger.info("method={}", request.getMethod());
        // ip
        logger.info("ip={}", request.getRemoteAddr());
        // 类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        // class_method=com.example.girl.controller.GirlController.girlList
//                                                                ↑
//        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + joinPoint.getSignature().getName());
//        class_method=com.example.girl.controller.GirlControllergirlList
//                                                               ↑
        // 参数
        logger.info("args={}", joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter() {
        logger.info("Request Complete");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object.toString());
    }
}

