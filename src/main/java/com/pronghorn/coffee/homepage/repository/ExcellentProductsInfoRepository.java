package com.pronghorn.coffee.homepage.repository;

import com.pronghorn.coffee.homepage.entity.ExcellentProductsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述:
 * 首页精品推荐 Repository
 *
 * @author wangguangkai
 * @create 2019-06-24 00:08
 */
public interface ExcellentProductsInfoRepository extends JpaRepository<ExcellentProductsInfo, Integer> {
    //查询可以使用的精品推荐
    List<ExcellentProductsInfo> getByIsAvailableEquals(String isAvailable);
}
