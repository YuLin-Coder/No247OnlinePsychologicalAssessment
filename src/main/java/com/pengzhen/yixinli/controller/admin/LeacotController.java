package com.pengzhen.yixinli.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pengzhen.yixinli.common.LoginSession;
import com.pengzhen.yixinli.common.ServerLayResult;
import com.pengzhen.yixinli.entity.Leacot;
import com.pengzhen.yixinli.entity.Reply;
import com.pengzhen.yixinli.service.LeacotService;
import com.pengzhen.yixinli.service.ReplyService;
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

/**
 * 后台留言控制器
 */
@Controller
public class LeacotController {

    @Autowired
    private LeacotService leacotService;

    @Autowired
    private ReplyService replyService;


    @RequestMapping("/leacotsView")
    public String leacotUi() {
        if (!LoginSession.getCurrentUser().getUsername().equals("admin")) {
            return "client/html/index";
        }
        return "admin/leacots/leacotsList";
    }


    /**
     * 用户留言列表
     *
     * @param page
     * @param limit
     * @param keyword1
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/leacots/list")
    public ServerLayResult leacotsList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "limit", defaultValue = "10") Integer limit, String keyword1) {
        if (keyword1 != null) {
            List<Leacot> leacots = leacotService.selectByKeyWord(page, limit, keyword1);
            ServerLayResult result = new ServerLayResult(0, "", leacots.size(), leacots);
            return result;
        }
        ServerLayResult result = new ServerLayResult(0, "", leacotService
                .count(), leacotService.selectAll(page, limit));
        return result;
    }

    /**
     * 根据ID删除 并且删除关联
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/leacots/del")
    public Map<String, Object> del(@RequestParam("id") int id) {
        Map<String, Object> dataMap = new HashMap<>();
        boolean isSuccess = false;
        Leacot leacot = leacotService.selectByPrimaryKey(id);
        //删除关联
        if (leacot != null) {
            boolean delReply = replyService.deleteByPrimaryKey(leacot.getId());
            if (delReply) {
                boolean delete = leacotService.deleteByPrimaryKey(id);
                isSuccess = true;
                dataMap.put("success", isSuccess);
                return dataMap;
            }
        }
        dataMap.put("success", isSuccess);
        return dataMap;
    }


    /**
     * {id: "4", leacotsUser: "test", content: "测试留言内容", replyContent: "fsafsf", status: "on"}
     *
     * @param json
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/leacots/update")
    public Map<String, Object> update(@RequestBody JSONObject json) {
        Map<String, Object> dataMap = new HashMap<>();
        boolean isSuccess = false;
        JSONObject data = JSON.parseObject(json.toJSONString());
        Integer id = data.getInteger("id");
        String leacotsUser = data.getString("leacotsUser");
        String content = data.getString("content");
        //回复内容
        String replyContent = data.getString("replyContent");
        //回复状态
        String status = data.getString("status");
        int temp = 0;
        if (status != null) {
            if (status.equals("on")) {
                temp = 1;
            }
        }
        //默认从session获得replyUser
        Reply reply = new Reply(id, replyContent, new Date(), "admin");
        //更新回复表
        boolean updateReply = replyService.updateByPrimaryKey(reply);
        Leacot leacot = new Leacot(id, content, new Date(), leacotsUser, reply, temp);
        //更新留言表
        boolean updateLeacot = leacotService.updateByPrimaryKey(leacot);
        if (updateLeacot && updateReply) {
            isSuccess = true;
            dataMap.put("success", isSuccess);
            return dataMap;
        }
        dataMap.put("success", isSuccess);
        return dataMap;
    }

}
