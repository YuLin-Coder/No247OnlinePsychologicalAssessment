package com.pengzhen.yixinli.controller.admin;

import com.pengzhen.yixinli.common.LoginSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConsoleController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/admin")
    public String index() {
        logger.info(LoginSession.getCurrentUser().getUsername());
        if (!LoginSession.getCurrentUser().getUsername().equals("admin")) {
            return "client/html/index";
        }
        return "admin/index";
    }


    @RequestMapping("/home/console")
    public String console() {
        if (!LoginSession.getCurrentUser().getUsername().equals("admin")) {
            return "client/html/index";
        }
        return "admin/home/console";
    }
}
