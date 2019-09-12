package com.pronghorn.coffee.user.repository;

import com.pronghorn.coffee.user.entity.UserAuthorInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 用户验证信息 repository
 *
 * @author wangguangkai
 * @create 2019-06-15 22:08
 */
public interface UserAuthorInfoRepository extends JpaRepository<UserAuthorInfo, String> {
}
