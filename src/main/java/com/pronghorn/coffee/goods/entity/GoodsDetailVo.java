package com.pronghorn.coffee.goods.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 描述:
 * 商品图片/名称/价格/ID 数据对象
 *
 * @author wangguangkai
 * @create 2019-06-20 23:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoodsDetailVo {
    private Integer id;
    private String imgPath;
    private String title;
    private BigDecimal price;
    private String goodsStandards;
    private String goodsSweetness;
    private String goodsTemperature;
    private String goodsDesc;
}
