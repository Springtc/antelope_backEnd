package com.pronghorn.coffee.user.repository;

import com.pronghorn.coffee.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:GuangKai Wang
 * @Description:
 * @Date: Create in 14:07 2019-06-04
 * @Modified By: 14:07 2019-06-04
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    public UserInfo getUserInfoByUserNameEquals(String userName);

    public UserInfo getUserInfoByPhoneEquals(String phone);
}
