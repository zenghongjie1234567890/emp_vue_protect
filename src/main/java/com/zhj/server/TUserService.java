package com.zhj.server;

import com.zhj.bean.TUser;

import java.util.List;

/**
 * emp_vue_protect
 *
 * @author : 曾小杰
 * @date : 2022-03-17 16:44
 **/
public interface TUserService {
    void addUser(TUser user);
    TUser loginUser(TUser user);
}
