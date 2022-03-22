package com.zhj.Dao;

import com.zhj.bean.TEmp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TEmpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TEmp record);

    TEmp selectByPrimaryKey(Integer id);

    List<TEmp> selectAll();

    int updateByPrimaryKey(TEmp record);
}