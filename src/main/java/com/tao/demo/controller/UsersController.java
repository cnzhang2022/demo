package com.tao.demo.controller;

import com.tao.demo.entity.Users;
import com.tao.demo.params.UsersParam;
import com.tao.demo.service.UsersService;
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

    @GetMapping(value = "/select", name = "接口")
    public List<Users> selectByMap(UsersParam params) {
        return this.usersService.selectByMap(params);
    }

    @GetMapping(value = "/query", name = "接口")
    public IPage<Users> query(int page, int size, UsersParam params) {
        return this.usersService.selectPage(page, size, params);
    }

}