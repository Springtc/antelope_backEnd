package com.pronghorn.coffee.user.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述:
 * 短信验证码验证登录
 *
 * @author wangguangkai
 * @create 2019-06-17 23:23
 */
@Entity
@Table(name = "MESSAGE_AUTHOR_INFO")
@Data
public class MessageAuthorInfo {
    @Id
    private String phone;
    private String message;
}
