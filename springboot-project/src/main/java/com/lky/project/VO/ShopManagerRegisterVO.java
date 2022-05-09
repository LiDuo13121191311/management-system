package com.lky.project.VO;

import com.lky.project.pojo.Shop;
import com.lky.project.pojo.ShopManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopManagerRegisterVO {
    public ShopManager userInfo;
    public Shop shopInfo;
}
