package com.jiang.meskill.service.model;

import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author jiangs
 * @create 2022-04-14-15:27
 */
@Data
public class PromoModel implements Serializable {
    private Integer id;

    //为1表示还未开始，为2表示进行中，3表示已结束
    private Integer status;

    //秒杀活动名称
    private String promoName;

    //秒杀活动的开始时间
    private DateTime startDate;

    //秒杀活动的结束时间
    private DateTime endDate;

    //秒杀活动的适用商品
    private Integer itemId;

    //秒杀活动的商品价格
    private BigDecimal promoItemPrice;

}
