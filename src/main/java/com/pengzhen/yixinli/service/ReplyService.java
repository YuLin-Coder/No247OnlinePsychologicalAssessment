package com.pengzhen.yixinli.service;

import com.pengzhen.yixinli.entity.Reply;

import java.util.List;

public interface ReplyService {

    boolean deleteByPrimaryKey(Integer id);

    boolean insert(Reply record);

    Reply selectByPrimaryKey(Integer id);

    List<Reply> selectAll();

    boolean updateByPrimaryKey(Reply record);
}
