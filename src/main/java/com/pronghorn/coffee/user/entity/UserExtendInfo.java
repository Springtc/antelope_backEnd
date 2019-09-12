package com.pronghorn.coffee.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "USER_EXTEND_INFO")
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class UserExtendInfo {
    @Id
    private String id;

    private String sex;//性别

    private Integer age;//年龄

    private Date birthday;//生日

    private String weChat;//微信

    private String qqNo;//QQ 号码

    private String email;//电子邮件

    private String school;//学校

    private String sno;//学号

    private Date termOfValidity;//有效期

    private BigDecimal remainder;//余额

    private String isAvailable;//是否可用
}
