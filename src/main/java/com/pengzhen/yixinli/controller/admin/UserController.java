package com.pengzhen.yixinli.controller.admin;

import com.pengzhen.yixinli.common.LoginSession;
import com.pengzhen.yixinli.common.ServerLayResult;
import com.pengzhen.yixinli.entity.User;
import com.pengzhen.yixinli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("/userUi")
    public String userUI() {
        if (!LoginSession.getCurrentUser().getUsername().equals("admin")) {
            return "client/html/index";
        }
        return "admin/user/userList";
    }

    @ResponseBody
    @RequestMapping("/admin/user/tableList")
    public ServerLayResult userList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                    @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        if (keyword == null || keyword.equals("")) {
            //封装Json数据对象
            ServerLayResult result = new ServerLayResult(0, "", userService.count(), userService.selectAll(page, limit));
            return result;
        } else if (keyword != null) {
            ServerLayResult result = new ServerLayResult(0, "", userService.selectByUsername(keyword, page, limit).size(),
                    userService.selectByUsername(keyword, page, limit));
            return result;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/admin/user/del")
    public Map<String, Object> del(@RequestParam("id") Integer id) {
        Map<String, Object> dataMap = new HashMap<>();
        Boolean isSuccess = false;
        if (id != null && id != 0) {
            int del = userService.deleteByPrimaryKey(id);
            if (del > 0) {
                isSuccess = true;
                dataMap.put("success", isSuccess);
                return dataMap;
            }
        }
        dataMap.put("success", isSuccess);
        return dataMap;
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/admin/user/update", method = RequestMethod.POST)
    public Map<String, Object> updateUser(@RequestBody User user) {
        Map<String, Object> dataMap = new HashMap<>();
        Boolean isSuccess = false;
        if (user != null) {
            //根据用户对象的id 查询用户的密码，防止密码丢失
            User user1 = userService.selectByPrimaryKey(user.getId());
            //再次封装进对象中
            if (user1 != null) {
                user.setPassword(user1.getPassword());
                //更新对象
                int update = userService.updateByPrimaryKey(user);
                if (update > 0) {
                    isSuccess = true;
                    dataMap.put("success", isSuccess);
                    return dataMap;
                }
            }
        }
        dataMap.put("success", isSuccess);
        return dataMap;
    }


    /**
     * 重置用户密码
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/user/resetPwd")
    public Map<String, Object> restPwd(@RequestParam("id") Integer id) {
        Map<String, Object> dataMap = new HashMap<>();
        Boolean isSuccess = false;
        if (id != null && id > 0) {
            //根据id查询出用户
            User user = userService.selectByPrimaryKey(id);
            //开始重置密码，重置密码使用的Spring提供的工具类进行对密码123456进行加密
            user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
            //调用更新方法
            int restPwd = userService.updateByPrimaryKey(user);
            if (restPwd > 0) {
                isSuccess = true;
                dataMap.put("success", isSuccess);
                return dataMap;
            }
        }
        dataMap.put("success", isSuccess);
        return dataMap;
    }

}
