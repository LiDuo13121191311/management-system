package com.lky.project.mapper;

import com.lky.project.pojo.ShopManager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopManagerMapper {
    /*根据商家用户名查询是否存在*/
     ShopManager findShopManagerByUsername(String username);

    /*根据商家身份证号查询是否存在*/
    ShopManager findShopManagerByIdentityNumber(String identityNumbr);

    /*根据手机号查询是否存在*/
    ShopManager findShopManagerByPhone(String phone);

    /*新增店铺管理员*/
    Integer shopManagerRegister(ShopManager shopManager);

    /*根据smid查找店长信息*/
    ShopManager findShopManagerBysmid(Integer smid);

    /*获取店铺管理人员总数*/
    Integer getShopManagerCount();

    /*分页获取店铺管理人员总数*/
    List<ShopManager> findAllShopManagerByPage(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);
}
