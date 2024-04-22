package com.pengzhen.yixinli.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengzhen.yixinli.entity.Topic;
import com.pengzhen.yixinli.mapper.TopicMapper;
import com.pengzhen.yixinli.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 心理评测题库业务Service
 */
@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        int del = topicMapper.deleteByPrimaryKey(id);
        if (del > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insert(Topic record) {
        try {
            record.setTopicTime(new Date());
            //从全局Session获得，操作用户
            record.setUserOp("admin");
            int insert = topicMapper.insert(record);
            if (insert > 0) {
                return true;
            } else {
                throw new RuntimeException("数据库异常...");
            }
        } catch (Exception e) {
            throw new RuntimeException("服务器异常..."+e.getMessage());
        }
    }

    @Override
    public Topic selectByPrimaryKey(Integer id) {
        return topicMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Topic> selectAll(int page, int limit) {
        //分页
        PageHelper.startPage(page, limit);
        List<Topic> topics = topicMapper.selectAll();
        PageInfo info = new PageInfo(topics);
        return info.getList();
    }

    @Override
    public List<Topic> selectByList() {
        return topicMapper.selectAll();
    }


    @Override
    public boolean updateByPrimaryKey(Topic record) {
        try {
            record.setTopicTime(new Date());
            //从全局Session获得，操作用户
            record.setUserOp("admin");
            int update = topicMapper.updateByPrimaryKey(record);
            if (update > 0) {
                return true;
            } else {
                throw new RuntimeException("数据库异常....");
            }
        } catch (Exception e) {
            throw new RuntimeException("服务器异常...."+e.getMessage());
        }
    }

    @Override
    public int count() {
        return topicMapper.count();
    }

    @Override
    public List<Topic> selectByKeyWord(Integer page, Integer limit, String keyword1) {
        PageHelper.startPage(page, limit);
        List<Topic> topics = topicMapper.selectByKeyWord(keyword1);
        PageInfo info = new PageInfo(topics);
        return info.getList();
    }
}
