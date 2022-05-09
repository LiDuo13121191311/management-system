package com.lky.project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.jnlp.IntegrationService;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    public Integer sid;
    private String logoSrc;
    private String shopName;
    private String license;
    private Integer stat;
    private String deliveryCost;
    private String minCost;
    private String totalSales;
    private Integer uid;
    private String phone;
    private String addressDetail;
    private String deliveryTime;
    private String desc;
}
