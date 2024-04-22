package com.pengzhen.yixinli.controller.admin;

import com.pengzhen.yixinli.common.LoginSession;
import com.pengzhen.yixinli.common.ServerLayResult;
import com.pengzhen.yixinli.entity.Topic;
import com.pengzhen.yixinli.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 心理测试控制器
 */
@Controller
public class TopicController {


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopicService topicService;

    @RequestMapping("/topicView")
    public String topicView() {
        if (!LoginSession.getCurrentUser().getUsername().equals("admin")) {
            return "client/html/index";
        }
        return "admin/topic/topicList";
    }


    /**
     * 题目列表显示和高级查询
     *
     * @param page
     * @param limit
     * @param keyword1
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/topic/list")
    public ServerLayResult list(@RequestParam("page") Integer page,
                                @RequestParam("limit") Integer limit, String keyword1) {
        logger.info("高级查询数据======"+keyword1);
        if (keyword1 != null) {
            List<Topic> topics = topicService.selectByKeyWord(page, limit, keyword1);
            ServerLayResult result = new ServerLayResult(0, "", topics.size(), topics);
            return result;
        }
        //封装数据
        ServerLayResult result = new ServerLayResult(0, "", topicService.count(), topicService.selectAll(page, limit));
        return result;
    }


    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/topic/del")
    public Map<String, Object> del(@RequestParam("id") Integer id) {
        Map<String, Object> dataMap = new HashMap<>();
        boolean delete = topicService.deleteByPrimaryKey(id);
        dataMap.put("status", delete);
        return dataMap;
    }


    /**
     * 更新
     *
     * @param topic
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/admin/topic/update", method = RequestMethod.POST)
    public Map<String, Object> update(@RequestBody Topic topic) {
        Map<String, Object> dataMap = new HashMap<>();
        boolean update = topicService.updateByPrimaryKey(topic);
        dataMap.put("status", update);
        return dataMap;
    }


    @ResponseBody
    @RequestMapping(value = "/admin/topic/add", method = RequestMethod.POST)
    public Map<String, Object> insert(@RequestBody Topic topic) {
        Map<String, Object> dataMap = new HashMap<>();
        boolean insert = topicService.insert(topic);
        dataMap.put("status",insert);
        return dataMap;
    }


}
