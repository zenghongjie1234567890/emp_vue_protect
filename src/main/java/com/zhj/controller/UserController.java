package com.zhj.controller;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.zhj.bean.TEmp;
import com.zhj.bean.TUser;
import com.zhj.server.TUserService;
import com.zhj.server.TempService;
import com.zhj.utils.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.util.*;

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

    /**
     *  添加新用户
     */
    @PostMapping("save")
    // 图片参数名一定要和前端的一样 @RequestParam("photo")该注解可以用别名，解决前后端命名参数名字不一样
    public Map<String ,Object> saveEmp(TEmp emp, @RequestParam("photo") MultipartFile multipartFile) throws IOException {
        log.info("员工信息:"+emp);
        log.info("图片信息:"+multipartFile.getOriginalFilename());
        HashMap<String, Object> map = new HashMap<>();
        try {
            // 头像保存: 保存客户端上传的图像到服务端保存
            String  newFileName = emp.getName()+ "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            multipartFile.transferTo(new File("E:\\CodeRoom\\emp_vue_protect\\src\\main\\resources\\static\\photo",newFileName));
            // 设置头像地址
            emp.setPath(newFileName);
            // 保存员工信息
            tempService.save(emp);
            map.put("state",true);
            map.put("msg","员工信息保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","员工信息保存失败");
        }
        return map;
    }

    /**
     *  删除一个员工
     */
    @DeleteMapping("/del")
    public Map<String,Object> delEmp(Integer id){
        HashMap<String, Object> map = new HashMap<>();
        tempService.delEmp(id);
        map .put("state",true);
        map .put("msg","该员工删除成功");
        return map;
    }

    /**
     *  获取一个员工的信息
     */
    @GetMapping("/getOne")
    public TEmp GetOneEmp(Integer id){
        TEmp one = tempService.getOne(id);
        return one;
    }

    /**
     *  获取一个员工的信息
     */
    @PostMapping("/update")
    // 图片参数名一定要和前端的一样 @RequestParam("photo")该注解可以用别名，解决前后端命名参数名字不一样
    public Map<String ,Object> updateEmp(TEmp emp, MultipartFile photo) throws IOException {
        log.info("员工信息:"+emp);
        HashMap<String, Object> map = new HashMap<>();
        try {
            if (photo!=null){
                log.info("图片信息:"+photo.getOriginalFilename());
                // 头像保存: 保存客户端上传的图像到服务端保存
                String  newFileName = emp.getName()+ "." + FilenameUtils.getExtension(photo.getOriginalFilename());
                photo.transferTo(new File("E:\\CodeRoom\\emp_vue_protect\\src\\main\\resources\\static\\photo",newFileName));
                // 设置头像地址
                emp.setPath(newFileName);
            }
            // 保存员工信息
            tempService.update(emp);
            log.info("员工信息:"+emp);
            map.put("state",true);
            map.put("msg","员工信息更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","员工信息更新失败");
        }
        return map;
    }
}
