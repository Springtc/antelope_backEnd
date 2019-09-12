package com.pronghorn.coffee.goods.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pronghorn.coffee.common.entity.FileInfo;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "GOODS_INFO")
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class GoodsInfo {
    @Id
    private Integer id;

    private String goodsName;//商品名称

    private BigDecimal goodsPrice;//商品价格

    private String goodsType;//商品类别

    @OneToOne
    @JoinColumn(name = "IMAGE_ID", referencedColumnName = "ID")
    private FileInfo fileInfo;

    private String goodsStandards;//商品规格

    private String goodsSweetness;//商品甜度

    private String goodsTemperature;//商品温度

    private String goodsDesc;//商品描述

    private String onSale;//是否在售

    private String isAvailable;//是否可用
}