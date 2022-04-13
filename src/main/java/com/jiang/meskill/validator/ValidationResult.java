package com.jiang.meskill.validator;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangs
 * @create 2022-04-13-20:12
 */
@Data
public class ValidationResult {
    //校验结果是否有错
    private boolean hasError=false;

    //存放错误信息的map
    private Map<String, String> errorMsgMap = new HashMap<>();

    public String getErrMsg(){
        return StringUtils.join(errorMsgMap.values().toArray(), ",");
    }


}
