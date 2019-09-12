package com.pronghorn.coffee.goods.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 描述:
 * 商品列表信息数据组装对象
 *
 * @author wangguangkai
 * @create 2019-06-20 23:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoodsListVo {
    private String title;
    private List<GoodsDetailVo> list;
}
