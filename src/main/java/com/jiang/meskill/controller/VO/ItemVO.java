package com.jiang.meskill.controller.VO;

import lombok.Data;
import org.joda.time.DateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author jiangs
 * @create 2022-04-14-10:59
 */
@Data
public class ItemVO {
    private Integer id;
    private String title;
    private BigDecimal price;
    private Integer stock;
    private String description;
    private Integer sales;
    private String imgUrl;

    //记录商品是否在秒杀活动中，0：没有秒杀活动，1：秒杀活动待开始 2：进行中
    private Integer promoStatus;

    private BigDecimal promoPrice;

    private Integer promoId;

    private String startDate;

}
