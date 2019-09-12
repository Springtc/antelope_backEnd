package com.pronghorn.coffee.homepage.service.impl;

import com.pronghorn.coffee.homepage.entity.ExcellentProductsInfo;
import com.pronghorn.coffee.homepage.repository.ExcellentProductsInfoRepository;
import com.pronghorn.coffee.homepage.service.ExcellentProductsInfoService;
import com.pronghorn.core.generic.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 描述:
 * 首页精品推荐 Service 实现
 *
 * @author wangguangkai
 * @create 2019-06-24 00:10
 */
@Service
@Transactional
public class ExcellentProductsInfoServiceImpl extends CommonServiceImpl<ExcellentProductsInfo, Integer> implements ExcellentProductsInfoService {
    @Autowired
    private ExcellentProductsInfoRepository excellentProductsInfoRepository;

    @Override
    public List<ExcellentProductsInfo> getByIsAvailableEquals(String isAvailable) {
        return excellentProductsInfoRepository.getByIsAvailableEquals(isAvailable);
    }
}
