package com.pronghorn.coffee.order.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "ORDER_GOODS_INFO")
@Data
public class OrderGoodsInfo {
    @Id
    private Integer id;
    private Integer orderInfoId;//订单 ID
    private BigDecimal goodsPrice;//商品价格
    private Integer goodsQty;//商品数量
    private String goodsStandards;//商品规格
    private String goodsSweetness;//商品甜度
    private String goodsTemperature;//商品温度
}
