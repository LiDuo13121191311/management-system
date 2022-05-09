package com.lky.project.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lky.project.VO.ShopManagerRegisterVO;
import com.lky.project.VO.ShopsVO;
import com.lky.project.VO.UsersVO;
import com.lky.project.mapper.ShopManagerMapper;
import com.lky.project.pojo.Delivery;
import com.lky.project.pojo.Shop;
import com.lky.project.pojo.ShopManager;
import com.lky.project.pojo.User;
import com.lky.project.service.DeliveryService;
import com.lky.project.service.ShopManagerService;
import com.lky.project.service.ShopService;
import com.lky.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
public class ManagerController {


    @Autowired
    ShopService shopService;

    @Autowired
    UserService userService;

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    ShopManagerService shopManagerService;

    /*查询所有店铺以及对应的店长*/
    @GetMapping("/findAllShopInfo")
    public String findAllShopAndShopManager(String currentPage,String pageSize){
        System.out.println("currentPage:"+currentPage);
        System.out.println("pageSize:"+pageSize);
        //首先查出所有的店铺（在开和冻结），然后根据uid获取对应的店长
        ShopsVO shops = shopService.findAllShopAndShopManager(currentPage, Integer.valueOf(pageSize));
        System.out.println("shops:"+shops);
        return JSON.toJSONString(shops,SerializerFeature.WriteMapNullValue);
    }

    /*查询所有的待办理的店铺申请*/
    @GetMapping("/findAllShopApplyFor")
    public String findAllShopApplyFor(String currentPage,String pageSize){
        List<ShopManagerRegisterVO> allShopApplyFor = shopService.findAllShopApplyFor(currentPage, Integer.valueOf(pageSize));
        System.out.println("findAllShopApplyFor:"+allShopApplyFor);
        if(allShopApplyFor!=null){
            return JSON.toJSONString(allShopApplyFor);
        }else {
            return "none";
        }

    }

    /*获取所有用户*/
    @GetMapping("/findAllUserByPage")
    public String findAllUserByPage(String currentPage,String pageSize){
        //UsersVO userVO = userService.findAllUserByPage(currentPage, Integer.valueOf(pageSize));
        HashMap<String, Object> hashMap = userService.findAllUserByPage(currentPage, Integer.valueOf(pageSize));
        //System.out.println("users:"+users);
        //对象转换成json数据时，会自动忽略为null的值，如果有为null的值需要显示，需要加参数
        //System.out.println("users:"+JSON.toJSONString(hashMap.get("users"), SerializerFeature.WriteMapNullValue));
        return JSON.toJSONString(hashMap,SerializerFeature.WriteMapNullValue);
    }

    /*分页查询所有骑手*/
    @GetMapping("/findAllRidersByPage")
    public String findAllRidersByPage(String currentPage,String pageSize){
        HashMap<String, Object> hashMap = deliveryService.findAllRidersByPage(currentPage, Integer.valueOf(pageSize));
        return JSON.toJSONString(hashMap,SerializerFeature.WriteMapNullValue);
    }

    /*分页查询所有店铺管理人员*/
    @GetMapping("/findAllShopManagerByPage")
    public String findAllShopManagerByPage(String currentPage,String pageSize){
        HashMap<String, Object> hashMap = shopManagerService.findAllShopManagerByPage(currentPage, Integer.valueOf(pageSize));
        return JSON.toJSONString(hashMap,SerializerFeature.WriteMapNullValue);
    }

    /*保存修改的店铺信息*/
    @PostMapping("/saveShopInfo")
    public String saveShopInfo(@RequestBody Shop shop){
        //System.out.println(shop);
        boolean flag = shopService.saveShopInfo(shop);
        if(flag){
            return "success";
        }
        return "fail";
    }

    /*修改店铺状态*/
    @GetMapping("/changeShopStat")
    public String changeShopStat(String stat,String sid){
        boolean flag = shopService.changeShopStat(stat, sid);
        if(flag){
            return "success";
        }
        return "fail";
    }

    /*同意店铺申请*/
    @GetMapping("/agreeShopApplyFor")
    public String agreeShopApplyFor(String sid){
        boolean flag = shopService.agreeShopApplyFor(sid);
        if(flag){
            return "success";
        }
        return "fail";
    }
}



