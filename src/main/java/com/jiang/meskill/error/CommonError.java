package com.jiang.meskill.error;

import com.jiang.meskill.response.CommonReturnType;

/**
 * @author jiangs
 * @create 2022-04-12-17:32
 */
public interface CommonError {
    int getErrorCode();
    String getErrorMsg();
    CommonError setErrorMsg(String msg);
}
