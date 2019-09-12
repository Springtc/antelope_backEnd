package com.pronghorn.coffee.homepage.service;

import com.pronghorn.coffee.homepage.entity.ExcellentProductsInfo;
import com.pronghorn.core.generic.CommonService;

import java.util.List;

/**
 * 描述:
 * 首页精品推荐 Service
 *
 * @author wangguangkai
 * @create 2019-06-24 00:09
 */
public interface ExcellentProductsInfoService extends CommonService<ExcellentProductsInfo, Integer> {
    //查询可以使用的精品推荐
    List<ExcellentProductsInfo> getByIsAvailableEquals(String isAvailable);
}
