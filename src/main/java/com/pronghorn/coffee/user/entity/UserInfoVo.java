package com.pronghorn.coffee.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 * 组装返回的用户对象
 *
 * @author wangguangkai
 * @create 2019-06-19 23:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class UserInfoVo {
    private UserInfo userInfo;
    private UserExtendInfo userExtendInfo;
}
