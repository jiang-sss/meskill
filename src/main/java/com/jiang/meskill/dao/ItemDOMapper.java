package com.jiang.meskill.dao;

import com.jiang.meskill.dataobject.ItemDO;

import java.util.List;

public interface ItemDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Wed Apr 13 20:59:50 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Wed Apr 13 20:59:50 CST 2022
     */
    int insert(ItemDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Wed Apr 13 20:59:50 CST 2022
     */
    int insertSelective(ItemDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Wed Apr 13 20:59:50 CST 2022
     */
    ItemDO selectByPrimaryKey(Integer id);
    List<ItemDO> listItem();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Wed Apr 13 20:59:50 CST 2022
     */
    int updateByPrimaryKeySelective(ItemDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Wed Apr 13 20:59:50 CST 2022
     */
    int updateByPrimaryKey(ItemDO row);
    void increaseSales(Integer id, Integer amount);
}