package com.jiang.meskill.controller;

import com.jiang.meskill.error.BusinessException;
import com.jiang.meskill.error.EmBusinessError;
import com.jiang.meskill.response.CommonReturnType;
import com.jiang.meskill.service.Impl.OrderServiceImpl;
import com.jiang.meskill.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author jiangs
 * @create 2022-04-14-14:13
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController{

    @Autowired
    private OrderServiceImpl orderService;

//    @Autowired
//    private HttpServletRequest request;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/createorder")
    public CommonReturnType createOrder(@RequestParam(name="itemId") Integer itemId,
                                        @RequestParam(name="amount") Integer amount,
                                        @RequestParam(name="promoId", required = false) Integer promoId,
                                        HttpServletRequest request) throws BusinessException {
        HttpSession session = request.getSession();
//        Boolean is_login = (Boolean) session.getAttribute("IS_LOGIN");
        String token = request.getParameterMap().get("token")[0];
        if(StringUtils.isEmpty(token)){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }
//        if(is_login==null||!is_login.booleanValue()){
//            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
//        }
//        UserModel userModel = (UserModel)session.getAttribute("LOGIN_USER");
        UserModel userModel = (UserModel) redisTemplate.opsForValue().get(token);
        if(userModel==null){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }
        orderService.createOrder(userModel.getId(), itemId, promoId,amount);
        return CommonReturnType.create(null);
    }
}
