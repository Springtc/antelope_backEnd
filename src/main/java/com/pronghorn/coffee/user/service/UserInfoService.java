package com.pronghorn.coffee.user.service;

import com.pronghorn.coffee.user.entity.UserInfo;
import com.pronghorn.core.generic.CommonService;

/**
 * @Author:GuangKai Wang
 * @Description:
 * @Date: Create in 10:38 2019-06-03
 * @Modified By: 10:38 2019-06-03
 */
public interface UserInfoService extends CommonService<UserInfo, String> {
    UserInfo getUserInfoByUserNameEquals(String userName);

    UserInfo getUserInfoByPhoneEquals(String phone);

    String getToken(UserInfo userInfo);
}
