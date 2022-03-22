package com.zhj.redis;

import org.apache.ibatis.cache.Cache;

/**
 * emp_vue_protect
 *
 * @author : 曾小杰
 * @date : 2022-03-21 22:57
 **/
public class RedisCache implements Cache {
    @Override
    public String getId() {
        return null;
    }

    @Override
    public void putObject(Object key, Object value) {

    }

    @Override
    public Object getObject(Object key) {
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }
}
