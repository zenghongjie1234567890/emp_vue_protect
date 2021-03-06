package com.zhj.Dao;

import com.zhj.bean.TUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    TUser selectByName(String name);

    TUser selectByUserName(String name);

    List<TUser> selectAll();

    int updateByPrimaryKey(TUser record);
}