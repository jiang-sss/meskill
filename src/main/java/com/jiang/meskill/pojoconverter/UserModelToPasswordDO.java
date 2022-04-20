package com.jiang.meskill.pojoconverter;

import com.jiang.meskill.dataobject.UserPasswordDO;
import com.jiang.meskill.service.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import sun.security.util.Password;

/**
 * @author jiangs
 * @create 2022-04-20-19:13
 */
@Mapper
public interface UserModelToPasswordDO {
    UserModelToPasswordDO INSTANCE = Mappers.getMapper(UserModelToPasswordDO.class);
    @Mapping(source = "id", target = "userId")
    @Mapping(target = "id", ignore = true)
    UserPasswordDO toPasswordDO(UserModel userModel);
}
