package com.zhj.controller;

import com.zhj.bean.TEmp;
import com.zhj.bean.TUser;
import com.zhj.server.TUserService;
import com.zhj.server.TempService;
import com.zhj.utils.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
@Slf4j  // 该注解可以打印日志信息
public class UserController {

    @Resource(name = "user")
    private TUserService tUserService;
    @Resource(name = "emp")
    private TempService tempService;

    /**
     * 注册用户
     */
    @PostMapping("register")
    public Map<String, Object> register(@RequestBody TUser user, String code, HttpServletRequest request) {
        log.info("用户信息:" + user);
        log.info("用户输入验证码:" + code);
        HashMap<String, Object> map = new HashMap<>();
        try {
            String key = (String) request.getServletContext().getAttribute("code");
            if (key.equalsIgnoreCase(code)) {
                tUserService.addUser(user);
                map.put("state", true);
                map.put("msg", "提示:注册成功");
            } else {
                throw new RuntimeException("验证码出现错误！！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            // e.getMessage能得到上一级报错里面的打印信息，即addUser方法里面抛的异常
            map.put("msg", "提示:注册失败.." + e.getMessage());
        }
        return map;
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Map<String, Object> loginUser(@RequestBody TUser tUser) {
        // 日志输出特有的占位符
        log.info("登录用户信息:[ {},{} ]", tUser.getUsername(), tUser.getPassword());
        HashMap<String, Object> map = new HashMap<>();
        try {
            TUser user = tUserService.loginUser(tUser);
            map.put("msg", "登录成功！");
            map.put("state", true);
            map.put("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "登录失败," + e.getMessage());
            map.put("state", false);
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

    /**
     *  获取全体员工数据
     */
    @PostMapping("/getAll")
    public Map<String,Object> findAll(){
        HashMap<String, Object> map = new HashMap<>();
        List<TEmp> allEmp = tempService.getAllEmp();
        map.put("list",allEmp);
        return map;
    }


}
