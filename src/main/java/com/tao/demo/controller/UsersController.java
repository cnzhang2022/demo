package com.tao.demo.controller;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.annotation.NacosProperty;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.tao.demo.entity.Users;
import com.tao.demo.params.UsersParam;
import com.tao.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
* @author zt on 2020-03-13 15:46:47
*/
@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    private UsersService usersService;

    @Autowired
    private Environment env;

    @NacosValue(value = "${a:0}", autoRefreshed = true)
    private int a;
    @NacosValue(value = "${b:0}", autoRefreshed = true)
    private int b;

    @GetMapping(value = "/select", name = "接口")
    public List<Users> selectByMap(UsersParam params) {
        System.out.println("aaa==="+a);
        System.out.println("bbb==="+b);
        System.out.println("env==="+env.getProperty("JAVA_HOME"));
        return this.usersService.selectByMap(params);
    }

    @GetMapping(value = "/query", name = "接口")
    public IPage<Users> query(int page, int size, UsersParam params) {
        return this.usersService.selectPage(page, size, params);
    }

}