package com.pronghorn.coffee.user.repository;

import com.pronghorn.coffee.user.entity.UserExtendInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExtendInfoRepository extends JpaRepository<UserExtendInfo, String> {
}
