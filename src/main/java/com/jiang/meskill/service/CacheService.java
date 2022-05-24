package com.jiang.meskill.service;

/**
 * @author jiangs
 * @create 2022-05-24-14:53
 */

//封装本地缓存操作类
public interface CacheService {
    //存方法
    void setCommonCache(String key, Object value);

    //取方法
    Object getCommonCache(String key);

}
