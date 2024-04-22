package com.pengzhen.yixinli.service;


import com.pengzhen.yixinli.entity.User;

import java.util.List;

public interface UserService {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll(int page, int limit);

    int updateByPrimaryKey(User record);

    List<User> selectByUsername(String username, int page, int limit);

    int count();

    User loginByUser(String username, String pwasswrod);

    User getByUsername(String username);

    List<User> selectAll();
}
