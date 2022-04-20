package com.jiang.meskill.pojoconverter;

import com.jiang.meskill.dataobject.UserDO;
import com.jiang.meskill.dataobject.UserPasswordDO;
import com.jiang.meskill.service.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author jiangs
 * @create 2022-04-20-19:08
 */
@Mapper
public interface UserInfoAndPasswordDOToUserModel {
    UserInfoAndPasswordDOToUserModel INSTANCE = Mappers.getMapper(UserInfoAndPasswordDOToUserModel.class);
    @Mapping(source = "userDO.id", target = "id")
    UserModel toUserModel(UserDO userDO, UserPasswordDO userPasswordDO);
}
