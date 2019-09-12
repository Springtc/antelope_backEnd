package com.pronghorn.coffee.user.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*用户认证信息*/
@Entity
@Table(name = "USER_AUTHOR_INFO")
@Data
public class UserAuthorInfo {
    @Id
    private String id;//用户主键 ID
    private String token;//token
}
