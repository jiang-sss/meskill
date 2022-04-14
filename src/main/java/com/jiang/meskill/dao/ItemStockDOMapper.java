package com.jiang.meskill.dao;

import com.jiang.meskill.dataobject.ItemStockDO;

public interface ItemStockDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Wed Apr 13 20:59:50 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Wed Apr 13 20:59:50 CST 2022
     */
    int insert(ItemStockDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Wed Apr 13 20:59:50 CST 2022
     */
    int insertSelective(ItemStockDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Wed Apr 13 20:59:50 CST 2022
     */
    ItemStockDO selectByPrimaryKey(Integer id);

    ItemStockDO selectByItemId(Integer itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Wed Apr 13 20:59:50 CST 2022
     */
    int updateByPrimaryKeySelective(ItemStockDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Wed Apr 13 20:59:50 CST 2022
     */
    int updateByPrimaryKey(ItemStockDO row);
}