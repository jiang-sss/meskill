package com.jiang.meskill.controller;

import com.jiang.meskill.error.BusinessException;
import com.jiang.meskill.error.EmBusinessError;
import com.jiang.meskill.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangs
 * @create 2022-04-12-18:36
 */
public class BaseController {
//    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception exception){
        Map<String, Object> responseData = new HashMap<>();
        if(exception instanceof BusinessException){
            BusinessException exception1 = (BusinessException) exception;
            responseData.put("errCode", exception1.getErrorCode());
            responseData.put("errMsg", exception1.getErrorMsg());
        }else{
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrorCode());
            responseData.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrorMsg());
        }
        return CommonReturnType.create(responseData, "fail");
    }
}
