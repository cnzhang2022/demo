package com.tao.demo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
* @author zt on 2020-04-23 11:05:19
*/
@Data
@Accessors(chain = true)
public class UsersDto {

    /**
    * 主键
    */
    private Integer id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 年龄
    */
    private Integer age;
    /**
    * 
    */
    private Integer maxNum;
}