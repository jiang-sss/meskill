package com.jiang.meskill.service;

import com.jiang.meskill.error.BusinessException;
import com.jiang.meskill.service.model.ItemModel;

import java.util.List;

/**
 * @author jiangs
 * @create 2022-04-14-10:21
 */
public interface ItemService {
    //创建商品
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    //获取商品列表
    List<ItemModel> listItem();

    //商品详情浏览
    ItemModel getByItemId(Integer id);

    //item及prommo model缓存模型
    ItemModel getByItemIdByCache(Integer id);

    //库存扣减
    boolean decreaseStock(Integer itemId, Integer amount);

    //销量增加
    void increaseSales(Integer itemId, Integer amount);
}
