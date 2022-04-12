package com.jiang.meskill.service;

import com.jiang.meskill.service.model.UserModel;
import org.springframework.stereotype.Service;

/**
 * @author jiangs
 * @create 2022-04-11-18:57
 */
public interface UserService {
    UserModel getUserById(Integer id);
}
