package com.pronghorn.coffee.user.service.impl;

import com.pronghorn.coffee.user.entity.UserExtendInfo;
import com.pronghorn.coffee.user.service.UserExtendInfoService;
import com.pronghorn.core.generic.CommonServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 描述:
 * 用户扩展信息 Service 实现
 *
 * @author wangguangkai
 * @create 2019-06-15 22:07
 */
@Service
@Transactional
public class UserExtendInfoServiceImpl extends CommonServiceImpl<UserExtendInfo, String> implements UserExtendInfoService {
}
