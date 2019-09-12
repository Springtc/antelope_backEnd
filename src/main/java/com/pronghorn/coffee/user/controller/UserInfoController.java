package com.pronghorn.coffee.user.controller;

import com.pronghorn.coffee.user.entity.UserAuthorInfo;
import com.pronghorn.coffee.user.entity.UserExtendInfo;
import com.pronghorn.coffee.user.entity.UserInfo;
import com.pronghorn.coffee.user.entity.UserInfoVo;
import com.pronghorn.coffee.user.service.MessageAuthorInfoService;
import com.pronghorn.coffee.user.service.UserAuthorInfoService;
import com.pronghorn.coffee.user.service.UserExtendInfoService;
import com.pronghorn.coffee.user.service.UserInfoService;
import com.pronghorn.core.helper.Md5Helper;
import com.pronghorn.core.jsonResponse.CommonResponse;
import com.pronghorn.core.jsonResponse.FailedResponse;
import com.pronghorn.core.jsonResponse.SuccessResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:GuangKai Wang
 * @Description:
 * @Date: Create in 10:41 2019-06-03
 * @Modified By: 10:41 2019-06-03
 */
@RestController
@RequestMapping(value = "/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoServiceImpl;
    @Autowired
    private MessageAuthorInfoService messageAuthorInfoServiceImpl;
    @Autowired
    private UserAuthorInfoService userAuthorInfoServiceImpl;
    @Autowired
    private UserExtendInfoService userExtendInfoServiceImpl;


    /**
     * 添加用户
     *
     * @param userInfo 用户JSON对象
     * @return
     */
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UserInfo")
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> register(@RequestBody UserInfo userInfo) throws Exception {
        UserInfo localUser = userInfoServiceImpl.getUserInfoByUserNameEquals(userInfo.getUserName());
        if (null != localUser) {
            return new ResponseEntity<CommonResponse>(FailedResponse.create("用户名已存在"), HttpStatus.FAILED_DEPENDENCY);
        } else {
            userInfo.setPassWord(Md5Helper.encryptionMd5(userInfo.getPassWord()));
            userInfoServiceImpl.saveAndFlush(userInfo);
            return new ResponseEntity<CommonResponse>(SuccessResponse.create(userInfo, "创建用户成功"), HttpStatus.OK);
        }
    }

    /**
     * 用户登录
     *
     * @param userName 用户名
     * @param passWord 明文密码
     * @return ResponseEntity
     */
    @ApiOperation(value = "用户名密码登录", notes = "根据用户名密码登录")
    @ApiImplicitParam(name = "userName", value = "用户名", required = true)
    @RequestMapping(value = "userLogin", method = RequestMethod.GET)
    public ResponseEntity<CommonResponse> login(@RequestParam String userName, @RequestParam String passWord) throws Exception {
        //根据用户名查询，如果不存在则返回用户不存在
        UserInfo userInfo = userInfoServiceImpl.getUserInfoByUserNameEquals(userName);
        if (null != userInfo) {
            String parsePassWord = Md5Helper.encryptionMd5(passWord);
            if (parsePassWord.equals(userInfo.getPassWord())) {
                return new ResponseEntity<CommonResponse>(SuccessResponse.create("登录成功!"), HttpStatus.OK);
            } else {
                return new ResponseEntity<CommonResponse>(FailedResponse.create("密码错误，登录失败!"), HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<CommonResponse>(FailedResponse.create("用户名不存在!"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 用户根据短信验证码登录
     *
     * @param phone   手机号
     * @param message 短信验证码
     * @return ResponseEntity
     */
    @ApiOperation(value = "用户根据短信验证码登录", notes = "用户根据短信验证码登录")
    @ApiImplicitParam(name = "phone", value = "手机号", required = true)
    @RequestMapping(value = "/message/{phone}/{message}", method = RequestMethod.GET)
    public ResponseEntity<CommonResponse> phoneLogin(@PathVariable("phone") String phone, @PathVariable("message") String message) throws Exception {
        //根据手机号验证码登录,如果没有注册就默认用户名是手机号,然后用户可以自行注册
        UserInfo userInfo = userInfoServiceImpl.getUserInfoByPhoneEquals(phone);
        String localMessage = messageAuthorInfoServiceImpl.getOne(phone).getMessage();
        if (localMessage.equals(message)) {
            if (null == userInfo) {
                userInfo = new UserInfo();
                userInfo.setPhone(phone);
                userInfo.setUserName(phone);
                userInfoServiceImpl.saveAndFlush(userInfo);
                UserExtendInfo userExtendInfo = new UserExtendInfo();
                userExtendInfo.setId(userInfo.getId());
                userExtendInfoServiceImpl.saveAndFlush(userExtendInfo);
            }
            String token = userInfoServiceImpl.getToken(userInfo);
            UserAuthorInfo userAuthorInfo = new UserAuthorInfo();
            userAuthorInfo.setId(userInfo.getId());
            userAuthorInfo.setToken(token);
            userAuthorInfoServiceImpl.saveAndFlush(userAuthorInfo);
            return new ResponseEntity<CommonResponse>(SuccessResponse.create(userAuthorInfo, "登录成功!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<CommonResponse>(FailedResponse.create("验证码错误，登录失败!"), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/getByUserId/{id}", method = RequestMethod.GET)
    public ResponseEntity<CommonResponse> getByUserId(@PathVariable("id") String id) throws Exception {
        UserInfo userInfo = userInfoServiceImpl.getOne(id);
        UserExtendInfo userExtendInfo = userExtendInfoServiceImpl.getOne(id);
        UserInfoVo userInfoVo = UserInfoVo.builder().userInfo(userInfo).userExtendInfo(userExtendInfo).build();
        return new ResponseEntity<CommonResponse>(SuccessResponse.create(userInfoVo, "登录成功!"), HttpStatus.OK);
    }

    //保存用户信息
    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> updateUser(@RequestBody UserInfoVo userInfoVo) throws Exception {
        UserInfo localUser = userInfoServiceImpl.getUserInfoByUserNameEquals(userInfoVo.getUserInfo().getUserName());
        if (null != localUser) {
            if (!localUser.getId().equalsIgnoreCase(userInfoVo.getUserInfo().getId())) {
                return new ResponseEntity<CommonResponse>(FailedResponse.create("用户名已存在"), HttpStatus.FAILED_DEPENDENCY);
            } else {
                userInfoServiceImpl.saveAndFlush(userInfoVo.getUserInfo());
                userExtendInfoServiceImpl.saveAndFlush(userInfoVo.getUserExtendInfo());
                return new ResponseEntity<CommonResponse>(SuccessResponse.create(userInfoVo, "修改信息成功!"), HttpStatus.OK);
            }
        } else {
            userInfoServiceImpl.saveAndFlush(userInfoVo.getUserInfo());
            userExtendInfoServiceImpl.saveAndFlush(userInfoVo.getUserExtendInfo());
            return new ResponseEntity<CommonResponse>(SuccessResponse.create(userInfoVo, "修改信息成功!"), HttpStatus.OK);
        }
    }
}
