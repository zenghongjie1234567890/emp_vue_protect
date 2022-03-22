package com.zhj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * emp_vue_protect
 *
 * @author : 曾小杰
 * @date : 2022-03-21 22:04
 **/
@Controller
public class fter {
    @RequestMapping("/")
    public String index(){
        return "zhj";
    }
}

