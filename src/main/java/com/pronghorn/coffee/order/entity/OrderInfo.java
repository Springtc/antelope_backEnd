package com.pronghorn.coffee.order.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ORDER_INFO")
@Data
public class OrderInfo {
    @Id
    private Integer id;
    private String orderNo;
    private Date createTime;
    private String isPay;//0 已付款 1 待付款
    private String type;//配送类型0 外卖 1 自提
    private BigDecimal actualAmount;//订单实际金额
    private BigDecimal receiveAmount;//收到的支付金额
}
