package com.pengzhen.yixinli.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pengzhen.yixinli.common.LoginSession;
import com.pengzhen.yixinli.entity.User;
import com.pengzhen.yixinli.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改密码
 */
@Controller
public class UpdatePasswordController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @RequestMapping("/updatePwdViwe")
    public String updatePwd() {
        if (!LoginSession.getCurrentUser().getUsername().equals("admin")) {
            return "client/html/index";
        }
        return "admin/system/pwdUpdate";
    }


    @ResponseBody
    @RequestMapping("/admin/sysPwd/update")
    public Map<String, Object> updatePwd(@RequestBody JSONObject json) {
        Map<String, Object> dataMap = new HashMap<>();
        boolean isSuccess = false;
        JSONObject data = JSON.parseObject(json.toJSONString());
        String oldPassword = data.getString("oldPassword");
        String password = data.getString("password");
        String repassword = data.getString("repassword");
        logger.info("====>" + oldPassword + "--" + password + "--" + repassword);
        //这里默认只能修改管理员密码
        User byUsername = userService.getByUsername(LoginSession.getCurrentUser().getUsername());

        logger.info("---->" + byUsername);
        if (byUsername != null) {
            //校验旧密码是否正确
            if (!byUsername.getPassword().equals(DigestUtils.md5DigestAsHex(oldPassword.getBytes()))) {
                dataMap.put("scueess", isSuccess);
                return dataMap;
            }
            //校验两次输入的密码是否匹配
            if (!password.equals(repassword)) {
                dataMap.put("scueess", isSuccess);
                return dataMap;
            }
            //更新数据库密码
            //创建用户对象
            User user = new User();
            user.setId(byUsername.getId());
            user.setUsername(byUsername.getUsername());
            user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            user.setAddress(byUsername.getAddress());
            user.setEmail(byUsername.getEmail());
            user.setPhone(byUsername.getPhone());
            user.setTocheck(byUsername.getTocheck());
            user.setUserType(byUsername.getUserType());
            user.setName(byUsername.getName());
            if (userService.updateByPrimaryKey(user) > 0) {
                isSuccess = true;
                dataMap.put("success", isSuccess);
                return dataMap;
            }
        }
        dataMap.put("success", isSuccess);
        return dataMap;
    }
}
