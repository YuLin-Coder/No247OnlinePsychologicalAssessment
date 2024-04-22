package com.pengzhen.yixinli.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pengzhen.yixinli.common.LoginSession;
import com.pengzhen.yixinli.common.ServerLayResult;
import com.pengzhen.yixinli.entity.Article;
import com.pengzhen.yixinli.entity.Label;
import com.pengzhen.yixinli.service.ArticleService;
import com.pengzhen.yixinli.service.LabelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ArticleController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ArticleService articleService;

    @Autowired
    private LabelService labelService;

    @RequestMapping("/articleUi")
    public String articleListUi() {
        if (!LoginSession.getCurrentUser().getUsername().equals("admin")) {
            return "client/html/index";
        }
        return "admin/article/list";
    }

    @RequestMapping("/articleUiAdd")
    public String articleAddUi() {
        if (!LoginSession.getCurrentUser().getUsername().equals("admin")) {
            return "client/html/index";
        }
        return "admin/article/listform";
    }

    /**
     * 后台文章列表
     *
     * @param page     当前页
     * @param limit    每页多少条
     * @param keyword1 关键字查询
     * @param keyword2
     * @param keyword3
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/article/list")
    public ServerLayResult<Article> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                         String keyword1, String keyword2, String keyword3) {
        if (keyword1 != null && keyword2 != "" || keyword2 != null && keyword2 != "" || keyword3 != null && keyword3 != "") {
            List<Article> articles = articleService.selectByKeyWord(page, limit, keyword1, keyword2, keyword3);
            ServerLayResult result = new ServerLayResult(0, "", articles.size(), articles);
            return result;
        }
        //封装数据
        ServerLayResult result = new ServerLayResult(0, "", articleService.count(), articleService.selectAll(page, limit));
        return result;
    }


    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/article/del")
    public Map<String, Object> delArticle(@RequestParam("id") int id) {
        Map<String, Object> dataMap = new HashMap<>();
        boolean isSuccess = articleService.deleteByPrimaryKey(id);
        dataMap.put("success", isSuccess);
        return dataMap;
    }


    /**
     * 前台响应json数据
     * 解析保存
     *
     * @param article
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/article/add")
    public Map<String, Object> addArticle(@RequestBody JSONObject article) {
        JSONObject json = JSON.parseObject(article.toJSONString());
        String author = json.getString("author");
        Integer label = json.getInteger("label");
        String title = json.getString("title");
        String content = json.getString("content");
        String status = json.getString("status");
        int temp = 0;
        if (status != null) {
            if (status.equals("on")) {
                temp = 1;
            }
        }
        Label label1 = new Label();
        label1.setId(label);
        Article articles = new Article();
        articles.setAuthor(author);
        articles.setContent(content);
        articles.setTitle(title);
        articles.setStatus(temp);
        articles.setCreateTime(new Date());
        articles.setLabel(label1);
        logger.info(article + "");
        boolean isSuccess = articleService.insert(articles);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("success", isSuccess);
        return dataMap;
    }

    /**
     * 根据前台响应的json对象封装后通过业务方法保存到数据库
     *
     * @param article
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/article/update")
    public Map<String, Object> updateArticle(@RequestBody JSONObject article) {
        JSONObject json = JSON.parseObject(article.toJSONString());
        String author = json.getString("author");
        Integer label = json.getInteger("label");
        Integer id = json.getInteger("id");
        String title = json.getString("title");
        String content = json.getString("content");
        String status = json.getString("status");
        int temp = 0;
        if (status != null) {
            if (status.equals("on")) {
                temp = 1;
            }
        }
        Label label1 = new Label();
        label1.setId(label);
        Article articles = new Article();
        articles.setId(id);
        articles.setAuthor(author);
        articles.setContent(content);
        articles.setTitle(title);
        articles.setStatus(temp);
        articles.setCreateTime(new Date());
        articles.setLabel(label1);
        logger.info(article + "");
        boolean isSuccess = articleService.updateByPrimaryKey(articles);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("success", isSuccess);
        return dataMap;
    }

}


