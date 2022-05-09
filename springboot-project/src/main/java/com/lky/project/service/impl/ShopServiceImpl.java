package com.lky.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.lky.project.VO.*;
import com.lky.project.mapper.CategoryMapper;
import com.lky.project.mapper.GoodMapper;
import com.lky.project.mapper.ShopManagerMapper;
import com.lky.project.mapper.ShopMapper;
import com.lky.project.pojo.Category;
import com.lky.project.pojo.Good;
import com.lky.project.pojo.Shop;
import com.lky.project.pojo.ShopManager;
import com.lky.project.service.ShopService;
import com.lky.project.utils.PageTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class ShopServiceImpl implements ShopService {



    @Resource
    ShopMapper shopMapper;

    @Resource
    ShopManagerMapper shopManagerMapper;

    @Resource
    GoodMapper goodMapper;

    @Resource
    CategoryMapper categoryMapper;

    /*查询所有店铺*/
    @Override
    public List<Shop> findAllShop() {
         return shopMapper.findAllShop();
    }


    /*根据sid获取所有category下及其下的所有good*/
    @Override
    public List<GoodVO> findAllGoodsBySid(String sid) {
        List<GoodVO> goodList = new ArrayList<>();
        //1、根据sid获取所有的category
        List<Category> categories = shopMapper.findAllCategoryBySid(sid);
        //System.out.println("categories:"+categories);
        //2、根据category的id找到其下的所有good
        for (Category category : categories) {
            List<Good> goods = shopMapper.findAllGoodByCid(category.getCid());
            //System.out.println("goods:"+goods);
            //3、将categoryName和其下的good放进goodVO对象里
            GoodVO goodVO = new GoodVO(category.getCategoryName(), goods);
            goodList.add(goodVO);
        }
        //System.out.println(goodList);
        //System.out.println(JSON.toJSONString(goodList));
        return goodList;
    }

    /*根据sid获取shop*/
    @Override
    public Shop findShopBySid(Integer sid) {
        return shopMapper.findShopBySid(sid);
    }

    /*根据店铺license查询店铺是否存在*/
    @Override
    public Shop findShopByLicense(String license) {
        return shopMapper.findShopByLicense(license);
    }

    /*根据uid获取shop*/
    @Override
    public Shop findShopByUid(String uid) {
        return shopMapper.findShopByUid(uid);
    }

    /*获取所有店铺和对应的店长*/
    @Override
    public ShopsVO findAllShopAndShopManager(String currentPage, Integer pageSize){
        List<ShopManagerRegisterVO> shopManagerVOS = new ArrayList<>();
        Integer count = shopMapper.getShopsCount();
        PageTool pageTool = new PageTool(count, currentPage, pageSize);
        System.out.println(pageTool.getStartIndex());
        System.out.println(pageTool.getPageSize());
        List<Shop> shopList = shopMapper.findAllShopByPage(pageTool.getStartIndex(),pageTool.getPageSize());
        for (Shop shop : shopList) {
            //根据uid获取店长信息
            ShopManager shopManager = shopManagerMapper.findShopManagerBysmid(shop.getUid());
            shopManagerVOS.add(new ShopManagerRegisterVO(shopManager,shop));
        }
        return new ShopsVO(shopManagerVOS,count);
    }

    /*获取待办理的店铺申请*/
    @Override
    public List<ShopManagerRegisterVO> findAllShopApplyFor(String currentPage, Integer pageSize) {
        List<ShopManagerRegisterVO> shopManagerVOS = new ArrayList<>();
        //获取待办理的店铺申请总数
        Integer count = shopMapper.getShopApplyForCount();
        if(count!=0){
            PageTool pageTool = new PageTool(count, currentPage, pageSize);
            //获取待审核的店铺
            List<Shop> shopList = shopMapper.findAllShopApplyForByPage(pageTool.getStartIndex(), pageTool.getPageSize());
            for (Shop shop : shopList) {
                //获取店铺的店长信息
                ShopManager shopManager = shopManagerMapper.findShopManagerBysmid(shop.getUid());
                shopManagerVOS.add(new ShopManagerRegisterVO(shopManager,shop));
            }
            return shopManagerVOS;
        }else {
            return null;
        }

    }

    /*保存修改的店铺信息*/
    @Override
    public boolean saveShopInfo(Shop shop) {
        Integer row = shopMapper.saveShopInfo(shop);
        if(row>0){
            return true;
        }
        return false;
    }

    /*修改店铺状态*/
    @Override
    public boolean changeShopStat(String stat,String sid) {
        Integer row = shopMapper.changeShopStat(stat, sid);
        if(row>0){
            return true;
        }
        return false;
    }

    /*同意店铺申请*/
    @Override
    public boolean agreeShopApplyFor(String sid) {
        Integer row = shopMapper.agreeShopApplyFor(sid);
        if(row>0){
            return true;
        }
        return false;
    }

    /*模糊查询店铺或商品*/
    @Override
    public List<Shop> findGoodsAndShopsByValue(String value) {
        //1、模糊查询店铺名
        List<Shop> shops = shopMapper.findShopsByShopNameLike(value);
        //2、模糊查询商品，从而找到对应的店铺
        List<Good> goods = goodMapper.findGoodByNameLike(value);
        for (Good good : goods) {
            Shop shop = shopMapper.findShopBySid(good.getSid());
            //店铺是否是经营状态并且在shops里不存在
            if(shop.getStat()==1&&!shops.contains(shop)){
                shops.add(shop);
            }
        }
        return shops;
    }

    /*修改店铺信息*/
    @Override
    public boolean updateShopMessage(Shop shop) {
        Integer row = shopMapper.updateShopMessage(shop);
        if(row>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteGood(Integer gid) {
        Integer row = goodMapper.deleteGood(gid);
        if(row>0){
            return true;
        }
        return false;
    }

    /*分页根据店长id获取店铺商品*/
    @Override
    public HashMap<String,Object> findGoodsAndCategoryByPage(String uid, String currentPage, int pageSize) {
        Shop shop = shopMapper.findShopByUid(uid);
        //根据店铺找到类别
        //1、根据sid获取所有的goods数量
        Integer count = goodMapper.findGoodCountBySid(shop.getSid());

        //2、根据sid分页获取good
        PageTool pageTool = new PageTool(count, currentPage, pageSize);
        List<GoodInfoVO> goods = goodMapper.findGoodByPage(shop.getSid(), pageTool.getStartIndex(), pageTool.getPageSize());
        //3、根据good的cid找到对应的类别
        for (GoodInfoVO good:goods) {
            //4、找到对应good的类别
            Category category = categoryMapper.findCategoryByCid(good.getCid());
            good.setCategory(category.getCategoryName());
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("goods", goods);
        hashMap.put("totalCount",count);
        return hashMap;
    }

    /*修改商品信息*/
    @Override
    public boolean updateGoodMessage(GoodInfoVO goodInfoVO) {
        Integer row = goodMapper.updateGoodMessage(goodInfoVO);
        if(row>0){
            return true;
        }
        return false;
    }

    /*添加新的商品*/
    @Override
    public boolean addGoodMessage(Good good) {
        try {
            Integer row = goodMapper.addGoodMessage(good);
            if(row>0){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }

    /*获取各个商品的销量*/
    @Override
    public HashMap<String, Object> getGoodData(String sid) {
        List<Good> goods = goodMapper.findAllGoodBySid(sid);
        List<GoodDataVO> list = new ArrayList<>();
        for (Good good : goods) {
            GoodDataVO goodDataVO = new GoodDataVO();
            goodDataVO.setName(good.getGoodName());
            goodDataVO.setValue(good.getSales());
            list.add(goodDataVO);
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("goods",list);
        return hashMap;
    }

    /*根据销售量降序获取店铺*/
    @Override
    public List<Shop> findShopOrderBySales() {
        return shopMapper.findShopOrderBySales();
    }


}
