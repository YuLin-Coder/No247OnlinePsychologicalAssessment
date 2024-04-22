package com.pengzhen.yixinli.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengzhen.yixinli.entity.PgTest;
import com.pengzhen.yixinli.mapper.PgTestMapper;
import com.pengzhen.yixinli.service.PgTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试结果
 */
@Service
public class PgTestServiceImpl implements PgTestService {

    //注入持久层组件
    @Autowired
    private PgTestMapper pgTestMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        int del = pgTestMapper.deleteByPrimaryKey(id);
        if (del > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insert(PgTest record) {
        try {
            int insert = pgTestMapper.insert(record);
            if (insert > 0) {
                return true;
            } else {
                throw new RuntimeException("数据库异常...");
            }
        } catch (Exception e) {
            throw new RuntimeException("服务器异常...." + e.getMessage());
        }
    }

    @Override
    public PgTest selectByPrimaryKey(Integer id) {
        return pgTestMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PgTest> selectAll(int page, int limit) {

        //分页
        PageHelper.startPage(page, limit);
        List<PgTest> pgTests = pgTestMapper.selectAll();
        PageInfo info = new PageInfo(pgTests);
        return info.getList();
    }

    @Override
    public List<PgTest> selectAll() {
        return pgTestMapper.selectAll();
    }

    @Override
    public boolean updateByPrimaryKey(PgTest record) {

        try {
            int update = pgTestMapper.updateByPrimaryKey(record);
            if (update > 0) {
                return true;
            } else {
                throw new RuntimeException("数据异常...");
            }
        } catch (Exception e) {
            throw new RuntimeException("服务器异常..." + e.getMessage());
        }
    }

    /**
     * 查询结果总数
     *
     * @return
     */
    @Override
    public int count() {
        return pgTestMapper.count();
    }

    @Override
    public List<PgTest> selectByKeyWord(int page, int limit, String keyword1) {
        //分页
        PageHelper.startPage(page, limit);
        //
        List<PgTest> pgTests = pgTestMapper.selectAllByKeyWord(keyword1);
        PageInfo info = new PageInfo(pgTests);
        return info.getList();
    }
}
