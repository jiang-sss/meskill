package com.jiang.meskill.dao;

import com.jiang.meskill.dataobject.UserDO;
import org.apache.catalina.User;

public interface UserDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Mon Apr 11 18:16:57 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Mon Apr 11 18:16:57 CST 2022
     */
    int insert(UserDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Mon Apr 11 18:16:57 CST 2022
     */
    int insertSelective(UserDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Mon Apr 11 18:16:57 CST 2022
     */
    UserDO selectByPrimaryKey(Integer id);

    UserDO selectByTelphone(String telphone);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Mon Apr 11 18:16:57 CST 2022
     */
    int updateByPrimaryKeySelective(UserDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Mon Apr 11 18:16:57 CST 2022
     */
    int updateByPrimaryKey(UserDO row);
}