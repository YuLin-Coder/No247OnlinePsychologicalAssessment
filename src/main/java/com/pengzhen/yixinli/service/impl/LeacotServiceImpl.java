package com.pengzhen.yixinli.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengzhen.yixinli.entity.Leacot;
import com.pengzhen.yixinli.mapper.LeacotMapper;
import com.pengzhen.yixinli.service.LeacotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 留言业务实现类
 */
@Service
public class LeacotServiceImpl implements LeacotService {

    @Autowired
    private LeacotMapper leacotMapper;

    /**
     * 根据ID三处
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        int del = leacotMapper.deleteByPrimaryKey(id);
        if (del > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insert(Leacot record) {
        try {
            int insert = leacotMapper.insert(record);
            if (insert > 0) {
                return true;
            } else {
                throw new RuntimeException("服务器中途喝咖啡去了，稍后就回来！");
            }
        } catch (Exception e) {
            throw new RuntimeException("服务器卡顿，留言添加失败！"+e.getMessage());
        }
    }

    @Override
    public Leacot selectByPrimaryKey(Integer id) {
        return leacotMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Leacot> selectAll(int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Leacot> leacots = leacotMapper.selectAll();
        PageInfo info = new PageInfo(leacots);
        return info.getList();
    }

    @Override
    public List<Leacot> selectByList() {
        return leacotMapper.selectAll();
    }


    @Override
    public boolean updateByPrimaryKey(Leacot record) {
        try {
            int update = leacotMapper.updateByPrimaryKey(record);
            if (update > 0) {
                return true;
            } else {
                throw new RuntimeException("服务器中途喝咖啡去了，稍后就回来！");
            }
        } catch (Exception e) {
            throw new RuntimeException("服务器卡顿，留言修改失败！"+e.getMessage());
        }
    }

    @Override
    public int count() {
        return leacotMapper.count();
    }

    @Override
    public List<Leacot> selectByKeyWord(Integer page, Integer limit, String keyword1) {
        return leacotMapper.selectByKeyWord(keyword1);
    }
}
