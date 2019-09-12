package com.pronghorn.coffee.user.service;

import com.pronghorn.coffee.user.entity.UserAddressInfo;
import com.pronghorn.core.generic.CommonService;

import java.util.List;

/**
 * 描述:
 * 用户收货地址 Service
 *
 * @author wangguangkai
 * @create 2019-06-20 22:50
 */
public interface UserAddressInfoService extends CommonService<UserAddressInfo, Integer> {
    List<UserAddressInfo> getByUserIdEquals(String userId);
}
