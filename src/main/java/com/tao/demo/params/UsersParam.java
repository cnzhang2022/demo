package com.tao.demo.params;

import lombok.Data;
import lombok.experimental.Accessors;

/**
* @author zt on 2020-04-23 11:05:19
*/
@Data
@Accessors(chain = true)
public class UsersParam {

/**
* 主键
*/
private Integer id;

    /**
    * 
    */
    private String USER;
    /**
    * 
    */
    private Long CURRENTCONNECTIONS;
    /**
    * 
    */
    private Long TOTALCONNECTIONS;
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