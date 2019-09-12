package com.pronghorn.coffee.user.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ADDRESS_INFO")
@Data
public class UserAddressInfo {
    @Id
    private Integer id;

    private String userId;//用户 ID

    private String address;//地址

    private String phone;//联系人电话

    private String reciveName;//收货人名称

    private String floatNum;//门牌号

    private String isDefault;//是否默认地址

    private String tag;//地址标签
}