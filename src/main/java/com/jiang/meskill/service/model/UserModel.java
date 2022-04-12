package com.jiang.meskill.service.model;

import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * @author jiangs
 * @create 2022-04-11-19:10
 */
@Data

public class UserModel {
    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;
    private String telphone;
    private String registerMode;
    private String thirdPartyId;

    private String encrptPassword;
}
