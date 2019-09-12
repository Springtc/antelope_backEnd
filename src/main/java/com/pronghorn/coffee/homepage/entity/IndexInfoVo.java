package com.pronghorn.coffee.homepage.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pronghorn.coffee.common.entity.CardInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 描述:
 * 首页访问数据对象
 *
 * @author wangguangkai
 * @create 2019-06-24 22:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class IndexInfoVo {
    private List<ExcellentProductsInfo> productsInfos;//精选商品推荐
    private List<CardInfo> cardInfos;//羚羊卡信息
}
