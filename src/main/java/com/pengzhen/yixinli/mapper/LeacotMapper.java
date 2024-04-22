package com.pengzhen.yixinli.mapper;

import com.pengzhen.yixinli.entity.Leacot;

import java.util.List;

public interface LeacotMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Leacot record);

    Leacot selectByPrimaryKey(Integer id);

    List<Leacot> selectAll();

    int updateByPrimaryKey(Leacot record);

    int count();

    List<Leacot> selectByKeyWord(String keyword1);
}