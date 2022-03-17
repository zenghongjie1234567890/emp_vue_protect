package com.zhj.server.serverImpl;

import com.zhj.Dao.TUserDao;
import com.zhj.bean.TUser;
import com.zhj.server.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * emp_vue_protect
 *
 * @author : 曾小杰
 * @date : 2022-03-17 16:44
 **/

@Service
public class TUserServiceImpl implements TUserService {
    @Autowired
    private TUserDao tUserDao;

    @Override
    public void addUser(TUser tUser) {
        tUser.setStatus("已经激活");
        tUser.setRegistertime(new Date());
        tUserDao.insert(tUser);
    }
}
