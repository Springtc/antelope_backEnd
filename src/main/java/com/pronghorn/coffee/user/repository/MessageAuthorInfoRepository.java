package com.pronghorn.coffee.user.repository;

import com.pronghorn.coffee.user.entity.MessageAuthorInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 短信验证码验证登录 repository
 *
 * @author wangguangkai
 * @create 2019-06-17 23:26
 */
public interface MessageAuthorInfoRepository extends JpaRepository<MessageAuthorInfo,String> {
}
