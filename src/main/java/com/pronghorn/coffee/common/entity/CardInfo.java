package com.pronghorn.coffee.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "CARD_INFO")
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class CardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cardName;//卡名称

    private String cardNo;//卡号

    private String cardType;//卡类型

    private String cardDesc;//卡片描述

    private BigDecimal cardAmount;//卡片金额

    private BigDecimal discount;//折扣

    private Date timeOfValidity;//有效期

    private String isAvailable;//是否可用

    @OneToOne
    @JoinColumn(name = "IMAGE_ID", referencedColumnName = "ID")
    private FileInfo fileInfo;

}
