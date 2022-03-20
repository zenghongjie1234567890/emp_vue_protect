package com.zhj.server.serverImpl;

import com.zhj.Dao.TUserDao;
import com.zhj.bean.TUser;
import com.zhj.server.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * emp_vue_protect
 *
 * @author : 曾小杰
 * @date : 2022-03-17 16:44
 **/

@Service("user")
public class TUserServiceImpl implements TUserService {
    @Autowired
    private TUserDao tUserDao;

    @Override
    public void addUser(TUser tUser) {
        // 判断用户是否存在
        TUser tUser1 = tUserDao.selectByName(tUser.getRealname());
        if (tUser1==null){
            tUser.setStatus("已经激活");
            tUser.setRegistertime(new Date());
            tUserDao.insert(tUser);
        }else {
            throw new RuntimeException("用户已经存在");
        }
    }

    @Override
    public TUser loginUser(TUser user) {
        // 1.根据用户输入用户名进行查询
        TUser tUser = tUserDao.selectByUserName(user.getUsername());
        if (tUser!=null){
            // 2.比较密码
            if (tUser.getPassword().equals(user.getPassword())){
                return tUser;
            }else {
                throw new RuntimeException("密码输入不正确!");
            }
        }else {
            throw new RuntimeException("暂无此用户，请重新输入！");
        }
    }

}
