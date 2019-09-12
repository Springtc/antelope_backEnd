package com.pronghorn.coffee.goods.service;

import com.pronghorn.coffee.goods.entity.GoodsInfo;
import com.pronghorn.coffee.goods.entity.GoodsListVo;
import com.pronghorn.core.generic.CommonService;
import com.pronghorn.core.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author:GuangKai Wang
 * @Description:
 * @Date: Create in 22:54 2019-06-01
 * @Modified By: 22:54 2019-06-01
 */
public interface GoodsInfoService extends CommonService<GoodsInfo, Integer> {
    List<GoodsListVo> getGoodsList();
}
