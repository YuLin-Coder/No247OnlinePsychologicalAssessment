package com.pengzhen.yixinli.service;

import com.pengzhen.yixinli.entity.Article;

import java.util.List;

/**
 * Service接口
 */
public interface ArticleService {

    boolean deleteByPrimaryKey(Integer id);

    boolean insert(Article record);

    Article selectByPrimaryKey(Integer id);

    List<Article> selectAll(int page,int limit);

    List<Article> selectClientAll();

    boolean updateByPrimaryKey(Article record);
    int count();

    List<Article> selectByKeyWord(int page, int limit,String keyword1,String keyword2,String keyword3);
}
