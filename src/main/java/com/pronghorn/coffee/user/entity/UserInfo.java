package com.pronghorn.coffee.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_INFO")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class UserInfo {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    private String imei;//手机 IMEI,标识设备唯一登录

    private String userName;//用户名

    private String passWord;//用户密码(密文)

    private String phone;//手机号码

}