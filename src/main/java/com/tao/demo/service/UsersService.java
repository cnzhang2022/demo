package com.tao.demo.service;

import org.springframework.stereotype.Service;
import com.tao.demo.dao.UsersMapper;
import com.tao.demo.entity.Users;
import com.tao.demo.dto.UsersDto;
import com.tao.demo.params.UsersParam;
import com.tao.frameworks.base.common.Result;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tao.frameworks.base.utils.MapUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;

/**
 * @author zt on 2020-04-23 13:41:32
 */
@Service
public class UsersService {

    @Resource
    private UsersMapper usersMapper;

    /**
     * 插入
     *
     * @param users 实体对象
     */
    public int insert(Users users){
        return usersMapper.insert(users);
    }

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    public int deleteById(Integer id){
        return usersMapper.deleteById(id);
    }

    /**
     * 修改
     *
     * @param users 实体对象
     */
    public int update(Users users){
        return usersMapper.updateById(users);
    }

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     * @return
     */
    public UsersDto getById(Integer id){
        Users users = usersMapper.selectById(id);
        if(users!=null){
            UsersDto usersDto = new UsersDto();
            BeanUtils.copyProperties(users, usersDto);
            return usersDto;
        }
        return null;
    }

    /**
     * 条件查询所有
     *
     * @param param
     * @return
     */
    public List<UsersDto> selectByMap(UsersParam param) {
        Map<String, Object> columnMap = MapUtils.beanToMap(param);
        List<Users> usersList = usersMapper.selectByMap(columnMap);
        List<UsersDto> usersDtoList = usersList.stream().map(users -> {
            UsersDto usersDto = new UsersDto();
            BeanUtils.copyProperties(users, usersDto);
            return usersDto;
        }).collect(Collectors.toList());
        return usersDtoList;
    }

    /**
     * 条件查询并分页
     *
     * @param page
     * @param limit
     * @param param
     * @return
     */
    public Result selectPage(int page, int limit, UsersParam param) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        Map<String, Object> columnMap = MapUtils.beanToMap(param);
        if(columnMap!=null) {
            columnMap.entrySet().forEach(entry -> queryWrapper.eq(entry.getKey(), entry.getValue()));
        }
        IPage<Users> p = new Page<>(page, limit);
        IPage<Users> ipage = usersMapper.selectPage(p, queryWrapper);
        List<Users> usersList = ipage.getRecords();
        List<UsersDto> usersDtoList = usersList.stream().map(users -> {
            UsersDto usersDto = new UsersDto();
            BeanUtils.copyProperties(users, usersDto);
            return usersDto;
        }).collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();
        map.put("pages", ipage.getPages());
        map.put("datas", usersDtoList);
        Result result = new Result(0, "success", map);
        return result;
    }

}