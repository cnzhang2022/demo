package com.tao.demo.service;

import com.tao.demo.dao.UsersMapper;
import com.tao.demo.entity.Users;
import com.tao.demo.params.UsersParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tao.frameworks.base.utils.MapUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author zt on 2020-03-13 15:46:47
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
     */
    public Users selectById(Integer id){
        return usersMapper.selectById(id);
    }

    /**
     * 条件查询
     *
     * @param param
     */
    public List<Users> selectByMap(UsersParam param) {
        Map<String, Object> columnMap = MapUtils.beanToMap(param);
        return usersMapper.selectByMap(columnMap);
    }

    /**
     * 条件查询并分页
     *
     * @param page
     * @param size
     * @param param
     */
    public IPage<Users> selectPage(int page, int size, UsersParam param) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        Map<String, Object> columnMap = MapUtils.beanToMap(param);
        if(columnMap!=null) {
            columnMap.entrySet().forEach(entry -> queryWrapper.eq(entry.getKey(), entry.getValue()));
        }
        IPage<Users> p = new Page<>(page, size);
        return usersMapper.selectPage(p, queryWrapper);
    }

}