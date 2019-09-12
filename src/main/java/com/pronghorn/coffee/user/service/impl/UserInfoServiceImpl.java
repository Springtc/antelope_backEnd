package com.pronghorn.coffee.user.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pronghorn.coffee.user.entity.UserInfo;
import com.pronghorn.coffee.user.repository.UserInfoRepository;
import com.pronghorn.coffee.user.service.UserInfoService;
import com.pronghorn.core.generic.CommonServiceImpl;
import com.pronghorn.core.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:GuangKai Wang
 * @Description:
 * @Date: Create in 10:40 2019-06-03
 * @Modified By: 10:40 2019-06-03
 */
@Service
public class UserInfoServiceImpl extends CommonServiceImpl<UserInfo, String> implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public UserInfo getUserInfoByUserNameEquals(String userName) {
        return userInfoRepository.getUserInfoByUserNameEquals(userName);
    }

    @Override
    public UserInfo getUserInfoByPhoneEquals(String phone) {
        return userInfoRepository.getUserInfoByPhoneEquals(phone);
    }

    @Override
    public String getToken(UserInfo userInfo) {
        String token="";
        token= JWT.create().withAudience(userInfo.getId())// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(userInfo.getPhone()));// 以 password 作为 token 的密钥
        return token;
    }
}
