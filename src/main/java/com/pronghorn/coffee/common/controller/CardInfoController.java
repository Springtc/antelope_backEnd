package com.pronghorn.coffee.common.controller;

import com.pronghorn.coffee.common.entity.CardInfo;
import com.pronghorn.coffee.common.service.CardInfoService;
import com.pronghorn.coffee.homepage.entity.HomePageInfo;
import com.pronghorn.core.jsonResponse.CommonResponse;
import com.pronghorn.core.jsonResponse.FailedResponse;
import com.pronghorn.core.jsonResponse.SuccessResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/cardInfo")
public class CardInfoController {
    @Autowired
    private CardInfoService cardInfoServiceImpl;

    @ApiOperation(value = "插入卡片信息", notes = "插入卡片信息")
    @ApiImplicitParam(name = "CardInfo", value = "卡片信息实体", required = true, dataType = "CardInfo")
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> inster(@RequestBody CardInfo model) {
        try {
            cardInfoServiceImpl.saveAndFlush(model);
            return new ResponseEntity<CommonResponse>(SuccessResponse.create(model, "插入成功"), HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<CommonResponse>(FailedResponse.create("插入失败，请联系管理员"), HttpStatus.FAILED_DEPENDENCY);
        }
    }
}
