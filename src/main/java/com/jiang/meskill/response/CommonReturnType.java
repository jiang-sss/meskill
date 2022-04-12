package com.jiang.meskill.response;

import lombok.Data;

/**
 * @author jiangs
 * @create 2022-04-11-19:49
 */
@Data
public class CommonReturnType {
    private String status;
    private Object data;

    public static CommonReturnType create(Object result){
        return create(result, "success");
    }

    public static CommonReturnType create(Object result, String status){
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.status = status;
        commonReturnType.data = result;
        return commonReturnType;
    }
}
