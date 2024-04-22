package com.pengzhen.yixinli.controller.client;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengzhen.yixinli.entity.Article;
import com.pengzhen.yixinli.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 前台文章显示
 */
@Controller
public class ClientArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 前台文章
     * @param page
     * @param limit
     * @param model
     * @return
     */
    @RequestMapping("/article/list")
    public String clientArticleList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "limit", defaultValue = "10") Integer limit, Model model) {
        PageHelper.startPage(page, limit);
        List<Article> articles = articleService.selectClientAll();
        PageInfo info = new PageInfo(articles);
        model.addAttribute("info", info);

        model.addAttribute("articleList", info.getList());
        //共享数据
        return "client/html/article";
    }

    /**
     * 前台查看文章
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/article/get")
    public String clientArticleById(@RequestParam("id") int id, Model model) {
        Article article = articleService.selectByPrimaryKey(id);
        model.addAttribute("article", article);
        int index = 1;
        model.addAttribute("index", ++index);
        return "client/html/articleDetails";
    }
}
