package com.pengzhen.yixinli.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户首页
 */
@Controller
public class ClientIndexController {


    @RequestMapping("/")
    public String clientIndex(){

        return "client/html/index";
    }
}
