package com.jiang.meskill.error;

/**
 * @author jiangs
 * @create 2022-04-12-17:36
 */
public enum EmBusinessError implements CommonError {
    //通用数据类型10001
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    UNKNOWN_ERROR(10002, "未知错误"),

    //20000开头表示用户信息相关错误
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_LOGIN_FAIL(20002, "用户手机号或密码错误"),
    USER_NOT_LOGIN(20003, "用户未登录"),

    //30000开头表示库存相关错误
    STOCK_NOT_ENOUGH(30001, "库存不足")
    ;

    private int errCode;
    private String errMsg;

    private EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }


    @Override
    public int getErrorCode() {
        return errCode;
    }

    @Override
    public String getErrorMsg() {
        return errMsg;
    }

    @Override
    public CommonError setErrorMsg(String msg) {
        this.errMsg=msg;
        return this;
    }
}
