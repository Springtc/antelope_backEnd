package com.pronghorn.coffee.goods.service.impl;

import com.pronghorn.coffee.goods.entity.GoodsDetailVo;
import com.pronghorn.coffee.goods.entity.GoodsInfo;
import com.pronghorn.coffee.goods.entity.GoodsListVo;
import com.pronghorn.coffee.goods.repository.GoodsInfoRepository;
import com.pronghorn.coffee.goods.service.GoodsInfoService;
import com.pronghorn.core.generic.CommonServiceImpl;
import com.pronghorn.core.generic.GenericServiceImpl;
import com.pronghorn.core.helper.FilePathHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:GuangKai Wang
 * @Description:
 * @Date: Create in 22:54 2019-06-01
 * @Modified By: 22:54 2019-06-01
 */
@Service
public class GoodsInfoServiceImpl extends CommonServiceImpl<GoodsInfo, Integer> implements GoodsInfoService {
    @Autowired
    private GoodsInfoRepository goodsInfoRepository;

    @Override
    public List<GoodsListVo> getGoodsList() {
        List<GoodsInfo> list = goodsInfoRepository.getByOnSaleEqualsAndIsAvailableEquals("Y", "Y");
        Map<String, List<GoodsDetailVo>> map = new LinkedHashMap<>();
        for (GoodsInfo g : list) {
            GoodsDetailVo gv = GoodsDetailVo.builder().id(g.getId()).imgPath("/coffee/img/" + g.getFileInfo().getFileAddress()).title(g.getGoodsName()).price(g.getGoodsPrice()).goodsDesc(g.getGoodsDesc()).goodsStandards(g.getGoodsStandards()).goodsSweetness(g.getGoodsSweetness()).goodsTemperature(g.getGoodsTemperature()).build();
            if (map.containsKey(g.getGoodsType())) {
                map.get(g.getGoodsType()).add(gv);
            } else {
                List<GoodsDetailVo> listDetail = new ArrayList<>();
                listDetail.add(gv);
                map.put(g.getGoodsType(), listDetail);
            }
        }
        List<GoodsListVo> voList = new ArrayList<>();
        for (String key : map.keySet()) {
            voList.add(GoodsListVo.builder().title(key).list(map.get(key)).build());
        }
        return voList;
    }
}
