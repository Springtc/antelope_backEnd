package com.pronghorn.coffee.homepage.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pronghorn.coffee.common.entity.FileInfo;
import lombok.Data;

import javax.persistence.*;

/**
 * 描述:
 * 首页精品推荐
 *
 * @author wangguangkai
 * @create 2019-06-24 00:07
 */
@Entity
@Table(name = "EXCELLENT_PRODUCTS_INFO")
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class ExcellentProductsInfo {
    @Id
    private int id;
    private String title;
    private String description;
    @OneToOne
    @JoinColumn(name = "IMAGE_ID", referencedColumnName = "ID")
    private FileInfo fileInfo;
    private String isAvailable;
}
