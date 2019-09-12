package com.pronghorn.coffee.homepage.repository;

import com.pronghorn.coffee.homepage.entity.HomePageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:GuangKai Wang
 * @Description:
 * @Date: Create in 18:02 2019-06-07
 * @Modified By: 18:02 2019-06-07
 */
public interface HomePageInfoRepository extends JpaRepository<HomePageInfo, Integer> {
}
