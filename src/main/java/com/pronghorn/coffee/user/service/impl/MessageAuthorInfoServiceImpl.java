package com.pronghorn.coffee.user.service.impl;

import com.pronghorn.coffee.user.entity.MessageAuthorInfo;
import com.pronghorn.coffee.user.service.MessageAuthorInfoService;
import com.pronghorn.core.generic.CommonServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 描述:
 * 短信验证码验证 Service 实现
 *
 * @author wangguangkai
 * @create 2019-06-17 23:29
 */
@Service
@Transactional
public class MessageAuthorInfoServiceImpl extends CommonServiceImpl<MessageAuthorInfo, String> implements MessageAuthorInfoService {
}
