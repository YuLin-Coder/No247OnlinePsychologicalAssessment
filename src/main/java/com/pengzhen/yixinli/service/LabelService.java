package com.pengzhen.yixinli.service;

import com.pengzhen.yixinli.entity.Label;

import java.util.List;

public interface LabelService {

    int deleteByPrimaryKey(Integer id);

    int insert(Label record);

    Label selectByPrimaryKey(Integer id);

    List<Label> selectAll();

    int updateByPrimaryKey(Label record);
}
