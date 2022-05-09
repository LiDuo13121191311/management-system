package com.lky.project.service.impl;

import com.lky.project.VO.ShopManagerRegisterVO;
import com.lky.project.mapper.ShopManagerMapper;
import com.lky.project.mapper.ShopMapper;
import com.lky.project.pojo.Shop;
import com.lky.project.pojo.ShopManager;
import com.lky.project.service.ShopManagerService;
import com.lky.project.service.ShopService;
import com.lky.project.utils.PageTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class ShopManagerServiceImpl implements ShopManagerService {

    @Resource
    ShopManagerMapper shopManagerMapper;

    @Resource
    ShopMapper shopMapper;

    /*根据商家用户名查询是否存在*/
    @Override
    public ShopManager findShopManagerByUsername(String username) {
        return shopManagerMapper.findShopManagerByUsername(username);
    }

    /*根据商家身份证号查询是否存在*/
    @Override
    public ShopManager findShopManagerByIdentityNumber(String identityNumbr) {
        return shopManagerMapper.findShopManagerByIdentityNumber(identityNumbr);
    }

    /*根据手机号查询是否存在*/
    @Override
    public ShopManager findShopManagerByPhone(String phone) {
        return shopManagerMapper.findShopManagerByPhone(phone);
    }

    /*店铺管理人员注册和店铺注册*/
    @Override
    public boolean shopManagerRegisterInfo(ShopManagerRegisterVO shopManagerRegisterVO) {
        System.out.println("shopManagerRegisterVO:"+shopManagerRegisterVO);
        //1、添加新的店铺管理员
        shopManagerMapper.shopManagerRegister(shopManagerRegisterVO.getUserInfo());
        //2、根据username查找刚添加的店铺管理员
        ShopManager shopManager = shopManagerMapper.findShopManagerByUsername(shopManagerRegisterVO.getUserInfo().getUsername());
        //3、新增店铺
        Shop shop = shopManagerRegisterVO.getShopInfo();
        shop.setUid(shopManager.getSmid());
        Integer row = shopMapper.addShop(shop);
        if(row>0){
            return true;
        }
        return false;
    }

    /*分页查询所有店铺管理人员*/
    @Override
    public HashMap<String,Object> findAllShopManagerByPage(String currentPage, Integer pageSize) {
        //获取店铺管理人员总数
        Integer count = shopManagerMapper.getShopManagerCount();
        PageTool pageTool = new PageTool(count, currentPage, pageSize);
        List<ShopManager> shopManagers = shopManagerMapper.findAllShopManagerByPage(pageTool.getStartIndex(), pageTool.getPageSize());
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("shopManagers",shopManagers);
        hashMap.put("count",count);
        return hashMap;
    }
}
