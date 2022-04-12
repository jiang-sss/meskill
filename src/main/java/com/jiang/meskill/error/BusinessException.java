package com.jiang.meskill.error;

import javax.crypto.interfaces.PBEKey;

/**
 * @author jiangs
 * @create 2022-04-12-17:47
 */
//包装器业务异常类实现
public class BusinessException extends Exception implements CommonError{
    private CommonError commonError;

    public BusinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }

    public BusinessException(CommonError commonError, String errMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrorMsg(errMsg);
    }

    @Override
    public int getErrorCode() {
        return commonError.getErrorCode();
    }

    @Override
    public String getErrorMsg() {
        return commonError.getErrorMsg();
    }

    @Override
    public CommonError setErrorMsg(String msg) {
        this.commonError.setErrorMsg(msg);
        return this;
    }
}
