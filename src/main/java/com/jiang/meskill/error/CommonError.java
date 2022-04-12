package com.jiang.meskill.error;

import com.jiang.meskill.response.CommonReturnType;

/**
 * @author jiangs
 * @create 2022-04-12-17:32
 */
public interface CommonError {
    public int getErrorCode();
    public String getErrorMsg();
    public CommonError setErrorMsg(String msg);
}
