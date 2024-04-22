package com.pengzhen.yixinli.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengzhen.yixinli.entity.Article;
import com.pengzhen.yixinli.mapper.ArticleMapper;
import com.pengzhen.yixinli.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 文章管理Service实现类
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        int del = articleMapper.deleteByPrimaryKey(id);
        if (del > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insert(Article record) {
        if (record.getAuthor() != null && !"".equals(record.getAuthor())) {
            //创建时间和更新时间
            record.setCreateTime(new Date());
            try {
                int insert = articleMapper.insert(record);
                if (insert > 0) {
                    return true;
                } else {
                    throw new RuntimeException("添加文章失败");
                }
            } catch (Exception e) {
                throw new RuntimeException("添加文章失败" + e.getMessage());
            }
        } else {
            throw new RuntimeException("文章作者不能为空！");
        }
    }

    @Override
    public Article selectByPrimaryKey(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Article> selectAll(int page, int limit) {
        //实现分页
        PageHelper.startPage(page, limit);
        List<Article> articles = articleMapper.selectAll();
        PageInfo info = new PageInfo(articles);
        return info.getList();
    }

    @Override
    public List<Article> selectClientAll() {

        return articleMapper.selectAll();
    }

    @Override
    public boolean updateByPrimaryKey(Article record) {
        if (record.getAuthor() != null && !"".equals(record.getAuthor())) {
            //创建时间和更新时间
            record.setCreateTime(new Date());
            try {
                int update = articleMapper.updateByPrimaryKey(record);
                if (update > 0) {
                    return true;
                } else {
                    throw new RuntimeException("添加文章失败");
                }
            } catch (Exception e) {
                throw new RuntimeException("添加文章失败" + e.getMessage());
            }
        } else {
            throw new RuntimeException("文章作者不能为空！");
        }
    }

    /**
     * 查询结果总数用于分页
     *
     * @return
     */
    @Override
    public int count() {
        return articleMapper.count();
    }

    @Override
    public List<Article> selectByKeyWord(int page, int limit, String keyword1, String keyword2, String keyword3) {
        //实现分页
        PageHelper.startPage(page, limit);
        List<Article> articles = articleMapper.selectByKeyWord(keyword1, keyword2, keyword3);
        PageInfo info = new PageInfo(articles);
        return info.getList();
    }
}
