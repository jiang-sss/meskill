package com.jiang.meskill.service.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jiangs
 * @create 2022-04-14-12:36
 */
@Data
//用户下单的交易模型
public class OrderModel {
    //交易单号
    private String id;

    //订单用户
    private Integer userId;

    //订单商品
    private Integer itemId;

    //若非空则以秒杀商品下单
    private Integer promoId;

    //冗余字段,若promoId非空，则表示秒杀商品价格
    private BigDecimal itemPrice;

    //购买数量
    private Integer amount;

    //购买金额
    private BigDecimal orderPrice;
}
