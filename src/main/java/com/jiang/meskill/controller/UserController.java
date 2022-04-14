package com.jiang.meskill.controller;

import com.alibaba.druid.util.StringUtils;
import com.jiang.meskill.controller.VO.UserVO;
import com.jiang.meskill.dao.UserDOMapper;
import com.jiang.meskill.error.BusinessException;
import com.jiang.meskill.error.EmBusinessError;
import com.jiang.meskill.response.CommonReturnType;
import com.jiang.meskill.service.Impl.UserServiceImpl;
import com.jiang.meskill.service.UserService;
import com.jiang.meskill.service.model.UserModel;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.nimbus.NimbusStyle;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static javax.swing.text.html.CSS.getAttribute;

/**
 * @author jiangs
 * @create 2022-04-11-18:53
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/login")
    public CommonReturnType login(@RequestParam(name = "telphone")String telphone,
                                  @RequestParam(name = "password")String password,
                                  HttpServletRequest request) throws BusinessException, NoSuchAlgorithmException {
        if(org.apache.commons.lang3.StringUtils.isEmpty(telphone)
                || org.apache.commons.lang3.StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号或密码不能为空");
        }
        UserModel userModel = userService.validateLogin(telphone, this.EncodeByMd5(password));

        //将用户登录凭证加入到登录成功的session中
        request.getSession().setAttribute("IS_LOGIN", true);
        request.getSession().setAttribute("LOGIN_USER", userModel);

        return CommonReturnType.create(null);
    }


    @RequestMapping("/register")
    public CommonReturnType register(@RequestParam(name = "telphone")String telphone,
                                     @RequestParam(name = "otpCode")String optCode,
                                     @RequestParam(name = "name")String name,
                                     @RequestParam(name = "gender")Byte gender,
                                     @RequestParam(name = "age")Integer age,
                                     @RequestParam(name = "password")String password,
                                     HttpServletRequest request) throws BusinessException, NoSuchAlgorithmException {
        //验证手机号和optCode相符
        HttpSession session = request.getSession();
        String sessionOtpCode = (String)session.getAttribute(telphone);
        if(!StringUtils.equals(optCode, sessionOtpCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "短信验证码不符合");
        }

        //用户注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(gender);
        userModel.setAge(age);
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byPhone");
        userModel.setEncrptPassword(EncodeByMd5(password));
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    public String EncodeByMd5(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String encode = base64Encoder.encode(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
        return encode;
    }


    @RequestMapping("/getotp")
    public CommonReturnType getOtp(@RequestParam(name = "telphone")String telphone, HttpServletRequest request){
        //需要按照一定规则生成opt验证码
        Random random = new Random();
        int i = random.nextInt(100000);//[0, 99999]
        i += 10000; //[10000,109999];
        String otpCode = String.valueOf(i);

        //讲otp验证码与用户手机号进行关联
        HttpSession session = request.getSession();
        session.setAttribute(telphone, otpCode);

        System.out.println("tel:"+telphone+", otpCode: "+otpCode);
        //讲opt验证码通过短信通道发送给用户
        return CommonReturnType.create(null);
    }

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
