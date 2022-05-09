package com.lky.project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    public Integer oid;
    private Integer uid;
    private Integer sid;
    private Integer aid;
    private Integer did;
    private String orderNumber;
    private BigDecimal goodTotalPrice;
    private String deliveryPrice;
    private BigDecimal totalPrice;
    private String createTime;
    private String orderTime;
    private String pickTime;
    private String arriveTime;
    private Integer orderStat;

    public Order(Integer oid, Integer uid, Integer sid, Integer aid, Integer did, String orderNumber, BigDecimal goodTotalPrice, String deliveryPrice, BigDecimal totalPrice, String createTime, Integer orderStat) {
        this.oid = oid;
        this.uid = uid;
        this.sid = sid;
        this.aid = aid;
        this.did = did;
        this.orderNumber = orderNumber;
        this.goodTotalPrice = goodTotalPrice;
        this.deliveryPrice = deliveryPrice;
        this.totalPrice = totalPrice;
        this.createTime = createTime;
        this.orderStat = orderStat;
    }
}
