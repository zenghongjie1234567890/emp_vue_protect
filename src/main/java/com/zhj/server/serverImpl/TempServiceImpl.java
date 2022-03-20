package com.zhj.server.serverImpl;

import com.zhj.bean.TEmp;
import com.zhj.server.TempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * emp_vue_protect
 *
 * @author : 曾小杰
 * @date : 2022-03-20 18:32
 **/

@Service("emp")
public class TempServiceImpl implements TempService {

    @Autowired
    TempService tempService;

    @Override
    public List<TEmp> getAllEmp() {
        List<TEmp> list = tempService.getAllEmp();
        return list;
    }
}
