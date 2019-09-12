package com.pronghorn.coffee.user.service.impl;

import com.pronghorn.coffee.user.entity.UserAddressInfo;
import com.pronghorn.coffee.user.repository.UserAddressInfoRepository;
import com.pronghorn.coffee.user.service.UserAddressInfoService;
import com.pronghorn.core.generic.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 描述:
 * 用户收货地址 Service 实现
 *
 * @author wangguangkai
 * @create 2019-06-20 22:51
 */
@Service
@Transactional
public class UserAddressInfoServiceImpl extends CommonServiceImpl<UserAddressInfo, Integer> implements UserAddressInfoService {
    @Autowired
    private UserAddressInfoRepository addressInfoRepository;

    @Override
    public List<UserAddressInfo> getByUserIdEquals(String userId) {
        return addressInfoRepository.getByUserIdEquals(userId);
    }
}
