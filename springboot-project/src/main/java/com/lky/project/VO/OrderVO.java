package com.lky.project.VO;

import com.lky.project.pojo.Address;
import com.lky.project.pojo.Good;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {
    private Integer oid;
    private Integer sid;
    private Integer uid;
    private Address addressMessage;
    private String deliveryPrice;
    private List<Good> goods;
    private BigDecimal totalPrice;
    private BigDecimal goodTotalPrice;
}
