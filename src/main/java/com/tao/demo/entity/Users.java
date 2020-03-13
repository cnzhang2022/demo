package com.tao.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @author zt on 2020-03-13 15:46:47
*/
@Data
@Accessors(chain = true)
public class Users {

/**
* 主键
*/
@TableId(type = IdType.AUTO)
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