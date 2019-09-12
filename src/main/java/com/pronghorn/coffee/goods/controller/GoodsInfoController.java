package com.pronghorn.coffee.goods.controller;

import com.pronghorn.coffee.goods.entity.GoodsInfo;
import com.pronghorn.coffee.goods.entity.GoodsListVo;
import com.pronghorn.coffee.goods.service.GoodsInfoService;
import com.pronghorn.coffee.user.entity.UserExtendInfo;
import com.pronghorn.coffee.user.entity.UserInfo;
import com.pronghorn.coffee.user.entity.UserInfoVo;
import com.pronghorn.core.generic.GenericController;
import com.pronghorn.core.jsonResponse.CommonResponse;
import com.pronghorn.core.jsonResponse.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:GuangKai Wang
 * @Description:
 * @Date: Create in 22:55 2019-06-01
 * @Modified By: 22:55 2019-06-01
 */
@RestController
@RequestMapping(value = "/goodsInfo")
public class GoodsInfoController {
    @Autowired
    private GoodsInfoService goodsInfoServiceImpl;

    @RequestMapping(value = "/getGoodsList", method = RequestMethod.GET)
    public ResponseEntity<CommonResponse> getByUserId() throws Exception {
        List<GoodsListVo> list = goodsInfoServiceImpl.getGoodsList();
        return new ResponseEntity<CommonResponse>(SuccessResponse.create(list, "查询数据成功!"), HttpStatus.OK);
    }

}
