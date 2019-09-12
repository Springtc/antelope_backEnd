package com.pronghorn.coffee.homepage.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pronghorn.coffee.common.entity.FileInfo;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author:GuangKai Wang
 * @Description:
 * @Date: Create in 18:00 2019-06-07
 * @Modified By: 18:00 2019-06-07
 */
@Entity
@Table(name = "home_page_info")
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class HomePageInfo {
    @Id
    private int id;
    private String title;
    private String description;
    private String userId;
    @OneToOne
    @JoinColumn(name = "IMAGE_ID", referencedColumnName = "ID")
    private FileInfo fileInfo;
    private Date createTime;
    private Date updateTime;
    private String status;
}
