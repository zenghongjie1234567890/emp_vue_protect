package com.zhj.server;

import com.zhj.bean.TEmp;
import com.zhj.bean.TUser;

import java.util.List;

/**
 * emp_vue_protect
 *
 * @author : 曾小杰
 * @date : 2022-03-20 18:32
 **/
public interface TempService {
    TEmp getOne(Integer id);

    List<TEmp> getAllEmp();

    void save(TEmp emp);

    void delEmp(Integer id);

    void update(TEmp emp);
}
