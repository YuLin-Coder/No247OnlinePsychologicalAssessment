package com.pengzhen.yixinli.controller.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengzhen.yixinli.common.LoginSession;
import com.pengzhen.yixinli.entity.Leacot;
import com.pengzhen.yixinli.entity.Reply;
import com.pengzhen.yixinli.service.LeacotService;
import com.pengzhen.yixinli.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户留言
 */
@Controller
public class ClientLeacotsController {

    @Autowired
    private LeacotService leacotService;
    @Autowired
    private ReplyService replyService;

    /**
     * 留言列表
     *
     * @param page
     * @param limit
     * @param model
     * @return
     */
    @RequestMapping("/leacotsUi")
    public String clientArticleUi(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit, Model model) {
        PageHelper.startPage(page, limit);
        List<Leacot> leacots = leacotService.selectByList();
        PageInfo info = new PageInfo(leacots);
        model.addAttribute("leacotsInfo", info);
        model.addAttribute("leacots", info.getList());
        //共享数据
        return "client/html/leacots";
    }


    /**
     * 用户留言
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/leacots/save", method = RequestMethod.POST)
    public Map<String, Object> saveLeacot(@RequestBody JSONObject data) {
        Map<String, Object> mapData = new HashMap<>();
        boolean isSuccess = false;
        JSONObject json = JSON.parseObject(data.toJSONString());
        //获取用户的留言内容
        String desc = json.getString("desc");
        //关联id
        int indexId = 4;
        //生成一条默认的用户评论
        Reply reply = new Reply();
        reply.setReplyContent("亲，管理员在快马加鞭的回复您！");
        reply.setReplyTime(new Date());
        reply.setReplyUser("管理员");
        reply.setId(indexId);
        boolean insertReply = replyService.insert(reply);
        if (insertReply) {
            Leacot leacot = new Leacot();
            //用户留言的内容
            leacot.setContent(desc);
            //用户留言时间
            leacot.setLeacotsTime(new Date());
            //默认后台没有回复
            leacot.setStatus(0);
            //在Session中获取
            leacot.setLeacotsUser(LoginSession.getCurrentUser().getUsername());
            //生成关联
            leacot.setReplyId(reply);
            boolean insertLeacot = leacotService.insert(leacot);
            isSuccess = true;
            mapData.put("success", isSuccess);
            //维护一下关联ID保证每一次进来的大于上一次的ID
            indexId++;
            return mapData;
        }

        mapData.put("success", isSuccess);
        return mapData;

    }
}
