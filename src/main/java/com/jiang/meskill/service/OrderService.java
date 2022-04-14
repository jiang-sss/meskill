package com.jiang.meskill.service;

/**
 * @author jiangs
 * @create 2022-04-14-12:52
 */

import com.jiang.meskill.error.BusinessException;
import com.jiang.meskill.service.model.OrderModel;

public interface OrderService {
    OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException;
}
