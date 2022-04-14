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

    //冗余字段
    private BigDecimal itemPrice;

    //购买数量
    private Integer amount;

    //购买金额
    private BigDecimal orderPrice;
}
