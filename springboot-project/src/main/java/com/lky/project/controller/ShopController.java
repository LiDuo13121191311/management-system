package com.lky.project.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;
import com.lky.project.VO.GoodInfoVO;
import com.lky.project.VO.GoodVO;
import com.lky.project.mapper.GoodMapper;
import com.lky.project.pojo.Good;
import com.lky.project.pojo.Shop;
import com.lky.project.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
public class ShopController {

    @Autowired
    ShopService shopService;

    /*查询所有店铺*/
    @GetMapping("/findAllShop")
    public String findAllShop() {
        List<Shop> shops = shopService.findAllShop();
        System.out.println(shops);
        return JSON.toJSONString(shops);
    }

    /*根据销售量降序获取店铺*/
    @GetMapping("/findShopOrderBySales")
    public String findShopOrderBySales(){
        List<Shop> shops = shopService.findShopOrderBySales();
        return JSON.toJSONString(shops,SerializerFeature.WriteMapNullValue);
    }

  /*根据店铺id获取店铺信息*/
    @GetMapping("/findShopBySid")
    public String findShopBySid(String sid){
        System.out.println(sid);
        Shop shop = shopService.findShopBySid(Integer.valueOf(sid));
        return JSON.toJSONString(shop);
    }


    /*根据店铺id获取商品*/
    @GetMapping("/findAllGoodsBySid")
    public String findAllGoodsBySid(String sid){
        //System.out.println(sid);
        List<GoodVO> goods = shopService.findAllGoodsBySid(sid);
        System.out.println(goods);
        return JSON.toJSONString(goods);
    }


    /*根据店铺license查询店铺是否存在*/
    @GetMapping("/findShopByLicense")
    public String findShopByLicense(String license){
        Shop shop = shopService.findShopByLicense(license);
        if(shop!=null){
            return "exist";
        }else {
            return "none";
        }
    }

    /*根据店长id查询店铺*/
    @GetMapping("/findShopByUid")
    public String findShopByUid(String uid){
        System.out.println(uid);
        Shop shop = shopService.findShopByUid(uid);
        return JSON.toJSONString(shop,SerializerFeature.PrettyFormat,SerializerFeature.WriteMapNullValue);
    }


    /*模糊查询店铺或商品*/
    @GetMapping("/findGoodsAndShopsByValue")
    public String findGoodsAndShopsByValue(String value){
        //System.out.println(value);
        List<Shop> shops = shopService.findGoodsAndShopsByValue(value);
        System.out.println("shops json:"+JSON.toJSONString(shops));
        return JSON.toJSONString(shops, SerializerFeature.PrettyFormat,SerializerFeature.WriteMapNullValue);
    }

    /*修改店铺信息*/
    @PostMapping("/updateShopMessage")
    public String updateShopMessage(@RequestBody Shop shop){
        System.out.println("shop:"+shop);
        boolean flag = shopService.updateShopMessage(shop);
        if(flag){
            return "success";
        }
        return "fail";
    }

    @GetMapping("/deleteGood")
    public String deleteGood(Integer gid){
        boolean flag = shopService.deleteGood(gid);
        if(flag){
            return "success";
        }
        return "fail";
    }

    /*分页根据店长id获取店铺商品*/
    @GetMapping("/findGoodsAndCategoryByPage")
    public String findGoodsAndCategoryByPage(String uid,String currentPage,String pageSize){
        HashMap<String, Object> hashMap = shopService.findGoodsAndCategoryByPage(uid, currentPage, Integer.parseInt(pageSize));
        if((int)hashMap.get("totalCount")!=0){
            return JSON.toJSONString(hashMap,SerializerFeature.WriteMapNullValue);
        }
        return null;
    }

    /*修改商品信息*/
    @PostMapping("/updateGoodMessage")
    public String updateGoodMessage(@RequestBody GoodInfoVO goodInfoVO){
        //System.out.println("goodInfoVO:"+goodInfoVO);
        boolean flag = shopService.updateGoodMessage(goodInfoVO);
        if(flag){
            return "success";
        }
        return "fail";
    }

    /*新增商品*/
    @PostMapping("/addGoodMessage")
    public String addGoodMessage(@RequestBody Good good){
        boolean flag = shopService.addGoodMessage(good);
        if(flag){
            return "success";
        }
        return "fail";

    }

    /*获取各个商品的销量*/
    @GetMapping("/getGoodData")
    public String getGoodData(String sid){
        HashMap<String, Object> hashMap = shopService.getGoodData(sid);
        return JSON.toJSONString(hashMap);
    }


}
