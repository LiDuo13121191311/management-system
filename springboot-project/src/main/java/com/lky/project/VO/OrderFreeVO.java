package com.lky.project.VO;

import com.lky.project.pojo.Address;
import com.lky.project.pojo.Order;
import com.lky.project.pojo.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderFreeVO {
    private String userName;
    private String userPhone;
    private Order order;
    private Shop shop;
    private Address address;
}
