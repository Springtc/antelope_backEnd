package com.pronghorn.coffee.user.controller;

import com.pronghorn.coffee.user.entity.UserAddressInfo;
import com.pronghorn.coffee.user.entity.UserExtendInfo;
import com.pronghorn.coffee.user.entity.UserInfo;
import com.pronghorn.coffee.user.entity.UserInfoVo;
import com.pronghorn.coffee.user.service.UserAddressInfoService;
import com.pronghorn.core.jsonResponse.CommonResponse;
import com.pronghorn.core.jsonResponse.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述:
 * 用户收货地址 Controller
 *
 * @author wangguangkai
 * @create 2019-06-20 22:54
 */
@RestController
@RequestMapping(value = "/userAddressInfo")
public class UserAddressInfoController {
    @Autowired
    private UserAddressInfoService userAddressInfoServiceImpl;

    //根据用户 ID 获取用户地址
    @RequestMapping(value = "/getByUserId/{id}", method = RequestMethod.GET)
    public ResponseEntity<CommonResponse> getByUserId(@PathVariable("id") String id) throws Exception {
        List<UserAddressInfo> list = userAddressInfoServiceImpl.getByUserIdEquals(id);
        return new ResponseEntity<CommonResponse>(SuccessResponse.create(list, "登录成功!"), HttpStatus.OK);
    }
}
