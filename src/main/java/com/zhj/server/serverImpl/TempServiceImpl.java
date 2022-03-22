package com.zhj.server.serverImpl;

import com.zhj.Dao.TEmpMapper;
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
    TEmpMapper tEmpMapper;

    @Override
    public TEmp getOne(Integer id) {
        return tEmpMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TEmp> getAllEmp() {
        List<TEmp> list =tEmpMapper.selectAll();
        return list;
    }

    @Override
    public void save(TEmp emp) {
        tEmpMapper.insert(emp);
    }

    @Override
    public void delEmp(Integer id) {
        tEmpMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(TEmp emp) {
        tEmpMapper.updateByPrimaryKey(emp);
    }
}
