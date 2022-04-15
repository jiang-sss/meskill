package com.jiang.meskill.service;

import com.jiang.meskill.service.model.PromoModel;

/**
 * @author jiangs
 * @create 2022-04-14-15:52
 */
public interface PromoService {
    PromoModel getPromoByItemId(Integer itemId);
}
