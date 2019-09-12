package com.pronghorn.coffee.homepage.service.impl;

import com.pronghorn.coffee.homepage.repository.HomePageInfoRepository;
import com.pronghorn.coffee.homepage.entity.HomePageInfo;
import com.pronghorn.coffee.homepage.service.HomePageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author:GuangKai Wang
 * @Description:
 * @Date: Create in 18:04 2019-06-07
 * @Modified By: 18:04 2019-06-07
 */
@Service
@Transactional
public class HomePageInfoServiceImpl implements HomePageInfoService {
    @Autowired
    private HomePageInfoRepository homePageInfoRepository;

    @Override
    public void save(HomePageInfo homePageInfo) {
        homePageInfoRepository.saveAndFlush(homePageInfo);
    }

    @Override
    public HomePageInfo getById(Integer id) {
        return homePageInfoRepository.getOne(id);
    }
}
