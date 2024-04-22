package com.pengzhen.yixinli.mapper;

import com.pengzhen.yixinli.entity.Article;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);
    //根据主键查询文章
    Article selectByPrimaryKey(Integer id);
    //查询全部的文章
    List<Article> selectAll();
    //根据文章的id更新文章
    int updateByPrimaryKey(Article record);
    //计数
    int count();

    //分页查询
    List<Article> selectByKeyWord(String keyword1,String keyword2,String keyword3);
}