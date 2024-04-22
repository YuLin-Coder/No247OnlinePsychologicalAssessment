package com.pengzhen.yixinli.controller.admin;

import com.pengzhen.yixinli.common.LoginSession;
import com.pengzhen.yixinli.common.ServerLayResult;
import com.pengzhen.yixinli.entity.Notice;
import com.pengzhen.yixinli.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公告管理
 */
@Controller
public class NoticeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("/noticeUi")
    public String articleListUi() {
        if (!LoginSession.getCurrentUser().getUsername().equals("admin")) {
            return "client/html/index";
        }
        return "admin/notice/noticeList";
    }

    @RequestMapping("/noticeUiAdd")
    public String articleAddUi() {
        if (!LoginSession.getCurrentUser().getUsername().equals("admin")) {
            return "client/html/index";
        }
        return "admin/notice/noticeAdd";
    }


    /**
     * 高级查询公告列表
     *
     * @param page
     * @param limit
     * @param keyword1
     * @param keyword2
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/notice/list")
    public ServerLayResult<Notice> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                        String keyword1, String keyword2) {
        logger.info("" + keyword1 + "---->" + keyword2);
        if (keyword1 != null && keyword1 != "" || keyword2 != null && keyword2 != "") {
            List<Notice> notices = noticeService.selectByKeyWord(page, limit, keyword1, keyword2);
            ServerLayResult result = new ServerLayResult(0, "", notices.size(), notices);
            return result;
        }
        //封装数据
        ServerLayResult result = new ServerLayResult(0, "", noticeService.count(), noticeService.selectAll(page, limit));
        return result;
    }


    /**
     * 根据ID删除公告
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/notice/del")
    public Map<String, Object> delNotice(@RequestParam("id") int id) {
        Map<String, Object> dataMap = new HashMap<>();
        boolean isSuccess = noticeService.deleteByPrimaryKey(id);
        dataMap.put("success", isSuccess);
        return dataMap;
    }


    /**
     * 更新公告
     *
     * @param notice
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/notice/save")
    public Map<String, Object> saveNotice(@RequestBody Notice notice) {
        Map<String, Object> dataMap = new HashMap<>();
        boolean isSuccess = noticeService.insert(notice);
        dataMap.put("success", isSuccess);
        return dataMap;
    }


    @ResponseBody
    @RequestMapping("/admin/notice/update")
    public Map<String, Object> updateNotice(@RequestBody Notice notice) {
        Map<String, Object> dataMap = new HashMap<>();
        boolean isSuccess = noticeService.updateByPrimaryKey(notice);
        dataMap.put("success", isSuccess);
        return dataMap;
    }
}
