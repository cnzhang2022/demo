package com.tao.demo.aop;

import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @LogAnnotation(operateModelNm = "测试方法", operateFuncNm = "保存方法")
    public String save(String name){
        System.out.println("我是save方法");
        return name;
    }

    public void update(String name){
        System.out.println("我是update方法");
    }
}
