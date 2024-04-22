package com.pengzhen.yixinli.controller.admin;

import com.pengzhen.yixinli.common.LoginSession;
import com.pengzhen.yixinli.common.ServerLayResult;
import com.pengzhen.yixinli.entity.PgTest;
import com.pengzhen.yixinli.service.PgTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理测试记录
 */

@Controller
public class PgTestController {

    @Autowired
    private PgTestService pgTestService;


    @RequestMapping("/pgtestView")
    public String pgtestView() {
        if (!LoginSession.getCurrentUser().getUsername().equals("admin")) {
            return "client/html/index";
        }
        return "admin/topic/pgtestList";
    }


    /**
     * 列表查询，高级查询
     *
     * @param page
     * @param limit
     * @param keyword1
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/pgtest/list")
    public ServerLayResult list(@RequestParam("page") int page, @RequestParam("limit") int limit, String keyword1) {
        if (keyword1 != null) {
            if (keyword1 != null && keyword1 != "") {
                List<PgTest> pgTests = pgTestService.selectByKeyWord(page, limit, keyword1);
                ServerLayResult result = new ServerLayResult(0, "", pgTests.size(), pgTests);
                return result;
            }
        }
        ServerLayResult result = new ServerLayResult(0, "", pgTestService.count(), pgTestService.selectAll(page, limit));
        return result;
    }


    /**
     * 根据ID删除评测记录
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/pgtest/del")
    public Map<String, Object> del(@RequestParam("id") Integer id) {
        Map<String, Object> dataMap = new HashMap<>();
        boolean delete = pgTestService.deleteByPrimaryKey(id);
        dataMap.put("status", delete);
        return dataMap;
    }
}
