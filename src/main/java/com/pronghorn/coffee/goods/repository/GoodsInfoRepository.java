package com.pronghorn.coffee.goods.repository;

import com.pronghorn.coffee.goods.entity.GoodsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述:
 * 商品信息 Repository
 *
 * @author wangguangkai
 * @create 2019-06-20 23:51
 */
public interface GoodsInfoRepository extends JpaRepository<GoodsInfo, Integer> {
    List<GoodsInfo> getByOnSaleEqualsAndIsAvailableEquals(String onSale, String isAvailable);
}
