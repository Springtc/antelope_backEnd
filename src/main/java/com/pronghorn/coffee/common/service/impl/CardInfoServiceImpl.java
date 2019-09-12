package com.pronghorn.coffee.common.service.impl;

import com.pronghorn.coffee.common.entity.CardInfo;
import com.pronghorn.coffee.common.repository.CardInfoRepository;
import com.pronghorn.coffee.common.service.CardInfoService;
import com.pronghorn.core.generic.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CardInfoServiceImpl extends CommonServiceImpl<CardInfo, Integer> implements CardInfoService {
    @Autowired
    private CardInfoRepository cardInfoRepository;

    @Override
    public List<CardInfo> getByIsAvailableEquals(String isAvailable) {
        return cardInfoRepository.getByIsAvailableEquals(isAvailable);
    }
}
