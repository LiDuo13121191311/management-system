package com.lky.project.service;

import com.lky.project.VO.GoodInfoVO;
import com.lky.project.VO.GoodVO;
import com.lky.project.VO.ShopManagerRegisterVO;
import com.lky.project.VO.ShopsVO;
import com.lky.project.pojo.Good;
import com.lky.project.pojo.Shop;

import java.util.HashMap;
import java.util.List;

public interface ShopService {
    /*查询所有店铺*/
    List<Shop> findAllShop();

    /*根据sid获取所有good*/
    List<GoodVO> findAllGoodsBySid(String sid);

    /*根据sid获取shop*/
    Shop findShopBySid(Integer sid);

    /*根据店铺license查询店铺是否存在*/
    Shop findShopByLicense(String license);

    /*根据uid获取shop*/
    Shop findShopByUid(String uid);

    /*获取所有店铺和对应的店长*/
    ShopsVO findAllShopAndShopManager(String currentPage, Integer pageSize);

    /*获取待办理的店铺申请*/
    List<ShopManagerRegisterVO> findAllShopApplyFor(String currentPage, Integer pageSize);

    /*保存修改的店铺信息*/
    boolean saveShopInfo(Shop shop);

    /*修改店铺状态*/
    boolean changeShopStat(String stat,String sid);

    /*同意店铺申请*/
    boolean agreeShopApplyFor(String sid);

    /*模糊查询店铺或商品*/
    List<Shop> findGoodsAndShopsByValue(String value);

    /*修改店铺信息*/
    boolean updateShopMessage(Shop shop);
    boolean deleteGood(Integer gid);

    /*分页根据店长id获取店铺商品*/
    HashMap<String,Object> findGoodsAndCategoryByPage(String uid, String currentPage, int pageSize);

    /*修改商品信息*/
    boolean updateGoodMessage(GoodInfoVO goodInfoVO);

    /*添加新的商品*/
    boolean addGoodMessage(Good good);

    /*获取各个商品的销量*/
    HashMap<String,Object> getGoodData(String sid);

    /*根据销售量降序获取店铺*/
    List<Shop> findShopOrderBySales();
}
