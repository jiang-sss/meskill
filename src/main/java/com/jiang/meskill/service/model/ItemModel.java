package com.jiang.meskill.service.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author jiangs
 * @create 2022-04-13-20:47
 */
@Data
public class ItemModel {
    private Integer id;

    //如果promoModel不为空，则表示其拥有还未结束的秒杀活动
    private PromoModel promoModel;

    @NotBlank(message = "商品名称不能为空")
    private String title;
    @NotNull(message = "商品价格不能为空")
    @Min(value = 0, message = "商品价格必须大于等于0")
    private BigDecimal price;
    @NotNull(message = "库存不能不填")
    private Integer stock;
    @NotBlank(message = "商品描述不能为空")
    private String description;
    private Integer sales;
    @NotBlank(message = "图片信息不能为空")
    private String imgUrl;
}
