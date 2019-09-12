package com.pronghorn.coffee.homepage.controller;

import com.pronghorn.coffee.common.entity.CardInfo;
import com.pronghorn.coffee.common.service.CardInfoService;
import com.pronghorn.coffee.homepage.entity.ExcellentProductsInfo;
import com.pronghorn.coffee.homepage.entity.HomePageInfo;
import com.pronghorn.coffee.homepage.entity.IndexInfoVo;
import com.pronghorn.coffee.homepage.service.ExcellentProductsInfoService;
import com.pronghorn.coffee.homepage.service.HomePageInfoService;
import com.pronghorn.core.jsonResponse.CommonResponse;
import com.pronghorn.core.jsonResponse.FailedResponse;
import com.pronghorn.core.jsonResponse.SuccessResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author:GuangKai Wang
 * @Description:
 * @Date: Create in 18:06 2019-06-07
 * @Modified By: 18:06 2019-06-07
 */
@RestController
@RequestMapping(value = "/homePageInfo")
public class HomePageInfoController {

    @Autowired
    HomePageInfoService homePageInfoServiceImpl;
    @Autowired
    private CardInfoService cardInfoServiceImpl;
    @Autowired
    private ExcellentProductsInfoService excellentProductsInfoServiceImpl;

    @ApiOperation(value = "根据ID查询首页广告", notes = "根据ID查询首页广告")
    @ApiImplicitParam(name = "ID", value = "首页广告ID", required = true, dataType = "Integer")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> getById(@PathVariable Integer id) {
        HomePageInfo homePageInfo = homePageInfoServiceImpl.getById(id);
        if (null != homePageInfo) {
            return new ResponseEntity<CommonResponse>(SuccessResponse.create(homePageInfo, "查询成功!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<CommonResponse>(FailedResponse.create("", "查询失败"), HttpStatus.FAILED_DEPENDENCY);
        }
    }

    @ApiOperation(value = "插入首页信息", notes = "插入首页广告")
    @ApiImplicitParam(name = "HomePageInfo", value = "首页广告实体", required = true, dataType = "HomePageInfo")
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> inster(@RequestBody HomePageInfo model) {
        try {
            homePageInfoServiceImpl.save(model);
            return new ResponseEntity<CommonResponse>(SuccessResponse.create(model, "插入成功"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<CommonResponse>(FailedResponse.create("插入失败，请联系管理员"), HttpStatus.FAILED_DEPENDENCY);
        }
    }

    //查询首页信息
    @RequestMapping(value = "/getIndexInfo", method = RequestMethod.GET)
    public ResponseEntity<CommonResponse> getIndexInfo() {
        List<CardInfo> cardInfo = cardInfoServiceImpl.getByIsAvailableEquals("Y");
        List<ExcellentProductsInfo> productsInfos = excellentProductsInfoServiceImpl.getByIsAvailableEquals("Y");
        IndexInfoVo indexInfoVo = IndexInfoVo.builder().cardInfos(cardInfo).productsInfos(productsInfos).build();
        return new ResponseEntity<CommonResponse>(SuccessResponse.create(indexInfoVo, "查询数据成功"), HttpStatus.OK);
    }
}
