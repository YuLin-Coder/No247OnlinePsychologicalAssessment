package com.pengzhen.yixinli.mapper;

import com.pengzhen.yixinli.entity.Notice;

import java.util.List;

/**
 * 公告接口
 */
public interface NoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    Notice selectByPrimaryKey(Integer id);

    List<Notice> selectAll();

    int updateByPrimaryKey(Notice record);

    List<Notice> selectByKeyWord(String keyword1, String keyword2);

    int count();


}