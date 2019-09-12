package com.pronghorn.coffee.user.repository;

import com.pronghorn.coffee.user.entity.UserAddressInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述:
 * 用户收货地址 repository
 *
 * @author wangguangkai
 * @create 2019-06-20 22:49
 */
public interface UserAddressInfoRepository extends JpaRepository<UserAddressInfo,Integer> {
    List<UserAddressInfo> getByUserIdEquals(String userId);
}
