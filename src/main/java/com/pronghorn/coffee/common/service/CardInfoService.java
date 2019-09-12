package com.pronghorn.coffee.common.service;

import com.pronghorn.coffee.common.entity.CardInfo;
import com.pronghorn.core.generic.CommonService;

import java.util.List;

public interface CardInfoService extends CommonService<CardInfo, Integer> {
    List<CardInfo> getByIsAvailableEquals(String isAvailable);
}
