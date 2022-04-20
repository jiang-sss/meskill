package com.jiang.meskill.pojoconverter;

import com.jiang.meskill.controller.VO.UserVO;
import com.jiang.meskill.service.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jiangs
 * @create 2022-04-20-19:42
 */
@Mapper
public interface UserModelToUserVO {
    UserModelToUserVO INSTANCE = Mappers.getMapper(UserModelToUserVO.class);
    UserVO toUserVO(UserModel userModel);
}
