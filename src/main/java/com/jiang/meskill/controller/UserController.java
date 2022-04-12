package com.jiang.meskill.controller;

import com.jiang.meskill.controller.VO.UserVO;
import com.jiang.meskill.error.BusinessException;
import com.jiang.meskill.error.EmBusinessError;
import com.jiang.meskill.response.CommonReturnType;
import com.jiang.meskill.service.UserService;
import com.jiang.meskill.service.model.UserModel;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.nimbus.NimbusStyle;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangs
 * @create 2022-04-11-18:53
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    public CommonReturnType getUser(@RequestParam(name="id") Integer id) throws BusinessException {
        UserModel user = userService.getUserById(id);

        //如果用户信息不存在
        if(user==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        return CommonReturnType.create(convertFromModel(user));
    }

    public UserVO convertFromModel(UserModel userModel){
        if(userModel==null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }
}
