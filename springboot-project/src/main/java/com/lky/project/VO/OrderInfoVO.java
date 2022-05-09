package com.lky.project.VO;

import com.lky.project.pojo.Address;
import com.lky.project.pojo.Good;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Administrator
 */
/*前端所需数据：  oid:'',
                        sid:'',
                        logoSrc:'/pic/kfcLogo.png',
                        shopName:'华莱士·全鸡汉堡',
                        orderStat:1,
                        goodTotalPrice,
                        totalPrice:'50',
                        createTime:'2021.8.9',
                        addressMessage:'',
                        goods:'',
                        orderNumber:'',
                        deliveryPrice:''
                        dmId:''
                        */

@Data
@AllArgsConstructor
@NoArgsConstructor
//
public class OrderInfoVO {
    private Integer oid;
    private Integer sid;
    private String logoSrc;
    private String shopName;
    private Integer orderStat;
    private BigDecimal goodTotalPrice;
    private String deliveryPrice;
    private BigDecimal totalPrice;
    private String createTime;
    private List<Good> goods;
    private Address addressMessage;
    private String orderNumber;
}
