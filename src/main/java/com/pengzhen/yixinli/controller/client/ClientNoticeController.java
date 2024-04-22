package com.pengzhen.yixinli.controller.client;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengzhen.yixinli.entity.Notice;
import com.pengzhen.yixinli.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 前端公告
 */
@Controller
public class ClientNoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("/noticeClientUi/list")
    public String clientNotice(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "limit", defaultValue = "10") Integer limit, Model model) {
        PageHelper.startPage(page, limit);
        List<Notice> notices = noticeService.selectAllClientNotice();
        PageInfo infoNotice = new PageInfo(notices);
        model.addAttribute("infoNotice", infoNotice);
        model.addAttribute("noticeList", infoNotice.getList());
        //共享数据
        return "client/html/notice";
    }

    /**
     * 前端页面详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/noticeClientUi/get")
    public String clientNoticeByGet(@RequestParam("id") int id, Model model) {
        Notice notice = noticeService.selectByPrimaryKey(id);
        model.addAttribute("notice", notice);
        int index = 1;
        model.addAttribute("index", ++index);
        return "client/html/noticeDetails";

    }
}
