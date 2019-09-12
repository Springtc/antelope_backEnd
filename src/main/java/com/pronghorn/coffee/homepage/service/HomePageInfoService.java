package com.pronghorn.coffee.homepage.service;

import com.pronghorn.coffee.homepage.entity.HomePageInfo;

/**
 * @Author:GuangKai Wang
 * @Description:
 * @Date: Create in 18:04 2019-06-07
 * @Modified By: 18:04 2019-06-07
 */
public interface HomePageInfoService {
    public void save(HomePageInfo homePageInfo);

    public HomePageInfo getById(Integer id);
}
