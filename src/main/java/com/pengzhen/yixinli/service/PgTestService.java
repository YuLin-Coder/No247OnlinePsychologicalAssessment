package com.pengzhen.yixinli.service;

import com.pengzhen.yixinli.entity.PgTest;

import java.util.List;

public interface PgTestService {

    boolean deleteByPrimaryKey(Integer id);

    boolean insert(PgTest record);

    PgTest selectByPrimaryKey(Integer id);

    List<PgTest> selectAll(int page, int limit);

    List<PgTest> selectAll();

    boolean updateByPrimaryKey(PgTest record);

    int count();

    List<PgTest> selectByKeyWord(int page, int limit, String keyword1);
}
