package com.pengzhen.yixinli.service.impl;

import com.pengzhen.yixinli.entity.Reply;
import com.pengzhen.yixinli.mapper.ReplyMapper;
import com.pengzhen.yixinli.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 留言关联表
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;


    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        int del = replyMapper.deleteByPrimaryKey(id);
        if (del > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insert(Reply record) {
        try {
            int insert = replyMapper.insert(record);
            if (insert > 0) {
                return true;
            } else {
                throw new RuntimeException("数据库异常....");
            }
        } catch (Exception e) {
            throw new RuntimeException("后台异常...."+e.getMessage());
        }
    }

    @Override
    public Reply selectByPrimaryKey(Integer id) {
        return replyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Reply> selectAll() {
        return replyMapper.selectAll();
    }

    @Override
    public boolean updateByPrimaryKey(Reply record) {
        try {
            int update = replyMapper.updateByPrimaryKey(record);
            if (update > 0) {
                return true;
            } else {
                throw new RuntimeException("数据库异常...");
            }
        } catch (Exception e) {
            throw new RuntimeException("服务器异常...." + e.getMessage());
        }
    }
}
