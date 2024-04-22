package com.pengzhen.yixinli.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengzhen.yixinli.entity.User;
import com.pengzhen.yixinli.mapper.UserMapper;
import com.pengzhen.yixinli.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * 用户ServiceImpl
 */

@Service
public class UserServiceImpl implements UserService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAll(int page, int limit) {
        //进行分页
        PageHelper.startPage(page, limit);
        List<User> users = userMapper.selectAll();
        PageInfo info = new PageInfo(users);
        return info.getList();
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<User> selectByUsername(String username, int page, int limit) {
        //进行分页
        PageHelper.startPage(page, limit);
        List<User> users = userMapper.selectByUsername(username);
        PageInfo info = new PageInfo(users);
        return info.getList();
    }

    @Override
    public int count() {
        return userMapper.count();
    }

    @Override
    public User loginByUser(String username, String password) {
        if (username != null && password != null) {
            return userMapper.loginByUser(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        }
        return null;
    }

    //修改密码
    @Override
    public User getByUsername(String username) {
        if (username != null) {
            return userMapper.getByUsername(username);
        }
        return null;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
