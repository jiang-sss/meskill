package com.jiang.meskill.pojoconverter;

import com.jiang.meskill.dataobject.UserDO;
import com.jiang.meskill.service.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jiangs
 * @create 2022-04-20-19:10
 */
@Mapper
public interface UserModelToUserDO {
    UserModelToUserDO INSTANCE = Mappers.getMapper(UserModelToUserDO.class);
    UserDO toUserModel(UserModel userModel);
}
