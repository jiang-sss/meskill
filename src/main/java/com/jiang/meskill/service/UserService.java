package com.jiang.meskill.service;

import com.jiang.meskill.error.BusinessException;
import com.jiang.meskill.service.model.UserModel;

/**
 * @author jiangs
 * @create 2022-04-11-18:57
 */
public interface UserService {
    UserModel getUserById(Integer id);
    UserModel getUserByIdInCache(Integer id);
    void register(UserModel userModel) throws BusinessException;
    UserModel validateLogin(String telphone, String password) throws BusinessException;
}
