package com.pengzhen.yixinli.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengzhen.yixinli.entity.Notice;
import com.pengzhen.yixinli.mapper.NoticeMapper;
import com.pengzhen.yixinli.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        int del = noticeMapper.deleteByPrimaryKey(id);
        if (del > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insert(Notice record) {
        //更新时间
        record.setCreateTime(new Date());
        //从session中获取
        record.setUserOp("admin");
        try {
            //添加
            int insert = noticeMapper.insert(record);
            if (insert > 0) {
                return true;
            } else {
                throw new RuntimeException("公告添加失败！");
            }
        } catch (Exception e) {
            throw new RuntimeException("公告添加失败" + e.getMessage());
        }
    }

    @Override
    public Notice selectByPrimaryKey(Integer id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Notice> selectAll(int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Notice> notices = noticeMapper.selectAll();
        PageInfo info = new PageInfo(notices);
        return info.getList();
    }

    @Override
    public List<Notice> selectAllClientNotice() {
        return noticeMapper.selectAll();
    }

    @Override
    public boolean updateByPrimaryKey(Notice record) {
        //更新时间
        record.setCreateTime(new Date());
        //从session中获取
        record.setUserOp("admin");
        try {
            //添加
            int insert = noticeMapper.updateByPrimaryKey(record);
            if (insert > 0) {
                return true;
            } else {
                throw new RuntimeException("公告更新失败！");
            }
        } catch (Exception e) {
            throw new RuntimeException("公告更新失败！" + e.getMessage());
        }
    }

    /**
     * 高级查询
     *
     * @param page
     * @param limit
     * @param keyword1
     * @param keyword2
     * @return
     */
    @Override
    public List<Notice> selectByKeyWord(int page, int limit, String keyword1, String keyword2) {
        PageHelper.startPage(page, limit);
        List<Notice> notices = noticeMapper.selectByKeyWord(keyword1,keyword2);
        PageInfo info = new PageInfo(notices);
        return info.getList();
    }

    @Override
    public int count() {
        return noticeMapper.count();
    }
}
