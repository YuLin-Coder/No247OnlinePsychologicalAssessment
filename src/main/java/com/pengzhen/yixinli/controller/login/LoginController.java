package com.pengzhen.yixinli.controller.login;

import com.google.code.kaptcha.Producer;
import com.pengzhen.yixinli.common.LoginSession;
import com.pengzhen.yixinli.entity.User;
import com.pengzhen.yixinli.service.UserService;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * 登入控制器
 */
@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //注入
    @Autowired
    private Producer producer;

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String loginView() {
        return "admin/login";
    }

    @ResponseBody
    @RequestMapping("/login_do")
    public String loginDo(@RequestBody User user, HttpServletRequest request) {
        String vrifyCode = (String) request.getSession().getAttribute("vrifyCode");
        if (user != null) {
            User users = userService.loginByUser(user.getUsername(), user.getPassword());
            if (users == null) {
                return "passwordError";
            } else if (!vrifyCode.equals(user.getVercode())) {
                return "vrifyCodeErroe";
            }
            LoginSession.setUserInSession(users);
            request.getSession().setAttribute("loginName", users.getUsername());
            return "success";
        } else {
            return "userNull";
        }
    }

    /**
     * 生成验证码
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @GetMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = producer.createText();
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = producer.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    //注销处理
    @RequestMapping("/logout")
    public String invalidate(HttpSession session) {
        session.invalidate();
        return "redirect:login.jsp";
    }

}
