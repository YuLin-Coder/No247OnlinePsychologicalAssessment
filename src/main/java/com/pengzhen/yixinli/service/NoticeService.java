package com.pengzhen.yixinli.service;

import com.pengzhen.yixinli.entity.Notice;

import java.util.List;

/**
 * 公告服务接口
 */
public interface NoticeService {

    boolean deleteByPrimaryKey(Integer id);

    boolean insert(Notice record);

    Notice selectByPrimaryKey(Integer id);

    List<Notice> selectAll(int page,int limit);
    List<Notice> selectAllClientNotice();

    boolean updateByPrimaryKey(Notice record);
    List<Notice> selectByKeyWord(int page, int limit, String keyword1, String keyword2);

    int count();
}
