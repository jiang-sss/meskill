package com.jiang.meskill.dao;

import com.jiang.meskill.dataobject.UserPasswordDO;

public interface UserPasswordDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Mon Apr 11 18:16:57 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Mon Apr 11 18:16:57 CST 2022
     */
    int insert(UserPasswordDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Mon Apr 11 18:16:57 CST 2022
     */
    int insertSelective(UserPasswordDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Mon Apr 11 18:16:57 CST 2022
     */
    UserPasswordDO selectByPrimaryKey(Integer id);

    UserPasswordDO selectByUserId(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Mon Apr 11 18:16:57 CST 2022
     */
    int updateByPrimaryKeySelective(UserPasswordDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Mon Apr 11 18:16:57 CST 2022
     */
    int updateByPrimaryKey(UserPasswordDO row);
}