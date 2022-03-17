package com.zhj.controller;

import com.zhj.bean.TUser;
import com.zhj.server.TUserService;
import com.zhj.utils.VerifyCodeUtils;
import lombok.extern.slf4j.XSlf4j;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * emp_vue_protect
 *
 * @author : 曾小杰
 * @date : 2022-03-16 22:51
 **/
@RestController
@CrossOrigin // 运行跨域
@RequestMapping("user")  // 公共域名
public class UserController {

    @Autowired
    private TUserService tUserService;

    /**
     * 注册用户
     */
    @PostMapping("register")
    public Map<String, Object> register(@RequestBody TUser user, String code, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(user);
        try {
            String key = (String) request.getServletContext().getAttribute("code");
            if (key.equalsIgnoreCase(code)) {
                tUserService.addUser(user);
                map.put("state", true);
                map.put("msg", "提示:注册成功");
            } else {
                System.out.println("验证码出现错误！！！");
            }
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", "提示:注册失败");
        }
        return map;
    }

    /**
     * 生成验证码图片
     */
    @GetMapping("getImage")
    public String getImageCode(HttpServletRequest request) throws IOException {
        // 1.使用utils包里面的类生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        // 2.将验证码放入servletContext作用域
        request.getServletContext().setAttribute("code", code);
        // 3.将图片转为base64
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(150, 40, stream, code);
        return "data:image/png;base64," + Base64Utils.encodeToString(stream.toByteArray());
    }


}
