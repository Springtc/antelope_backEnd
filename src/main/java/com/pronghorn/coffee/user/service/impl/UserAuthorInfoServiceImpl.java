package com.pronghorn.coffee.user.service.impl;

import com.pronghorn.coffee.user.entity.UserAuthorInfo;
import com.pronghorn.coffee.user.service.UserAuthorInfoService;
import com.pronghorn.core.generic.CommonService;
import com.pronghorn.core.generic.CommonServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 描述:
 * 用户验证信息 Service 实现
 *
 * @author wangguangkai
 * @create 2019-06-15 22:09
 */
@Service
@Transactional
public class UserAuthorInfoServiceImpl extends CommonServiceImpl<UserAuthorInfo, String> implements UserAuthorInfoService {
}
