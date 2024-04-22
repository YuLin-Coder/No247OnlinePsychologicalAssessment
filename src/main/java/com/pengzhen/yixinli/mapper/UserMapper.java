package com.pengzhen.yixinli.mapper;

import com.pengzhen.yixinli.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    List<User> selectByUsername(String username);

    int count();

    User loginByUser(@Param("username") String username, @Param("password") String password);

    User getByUsername(String username);
}