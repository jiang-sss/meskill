package com.jiang.meskill.dao;

import com.jiang.meskill.dataobject.PromoDO;

public interface PromoDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Thu Apr 14 15:50:46 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Thu Apr 14 15:50:46 CST 2022
     */
    int insert(PromoDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Thu Apr 14 15:50:46 CST 2022
     */
    int insertSelective(PromoDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Thu Apr 14 15:50:46 CST 2022
     */
    PromoDO selectByPrimaryKey(Integer id);

    PromoDO selectByItemId(Integer itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Thu Apr 14 15:50:46 CST 2022
     */
    int updateByPrimaryKeySelective(PromoDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Thu Apr 14 15:50:46 CST 2022
     */
    int updateByPrimaryKey(PromoDO row);
}