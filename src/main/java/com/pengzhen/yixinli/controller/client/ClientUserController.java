package com.pengzhen.yixinli.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户信息控制器
 */
@Controller
public class ClientUserController {



    @RequestMapping("/userinfoView")
    public String userInfoView(){

        return "client/html/userinfo";
    }

}
