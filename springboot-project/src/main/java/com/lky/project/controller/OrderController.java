package com.lky.project.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lky.project.VO.NewOrderInfoVO;
import com.lky.project.VO.OrderFreeVO;
import com.lky.project.VO.OrderInfoVO;
import com.lky.project.VO.OrderVO;
import com.lky.project.pojo.Order;
import com.lky.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;


    /*创建订单*/
    @PostMapping("/createOrder")
    public String createOrder(@RequestBody OrderVO orderVO){
        System.out.println("orderVO:"+orderVO);
        Order order = orderService.createOrder(orderVO);
        if(order!=null){
            System.out.println(JSON.toJSONString(order));
            return JSON.toJSONString(order);
        }
        return "fail";
    }


    /*根据uid查找所有订单以及所需的数据*/
    @GetMapping("/findAllOrderByUid")
    public String findAllOrderByUid(String uid){
        //System.out.println(uid);
        //System.out.println(JSON.toJSONString(orderService.findAllOrderByUid(Integer.valueOf(uid))));
        Integer id = Integer.valueOf(uid);
        return JSON.toJSONString(orderService.findAllOrderByUid(id));
    }

    /*获取所有待接单的订单*/
    @GetMapping("/getFreeOrders")
    public String getFreeOrders(){
        List<OrderFreeVO> freeOrders = orderService.getFreeOrders();
        System.out.println(freeOrders);
        return JSON.toJSONString(freeOrders);
    }

    /*获取所有待商家接单的订单*/
    @GetMapping("/getAllNewOrderBySid")
    public String getAllNewOrderBySid(String sid,String currentPage,String pageSize){
        HashMap<String, Object> hashMap = orderService.getAllOrderBySidAndStat(sid, currentPage, Integer.valueOf(pageSize),1);
        return JSON.toJSONString(hashMap,SerializerFeature.WriteMapNullValue);
    }

    /*获取所有待商家接单的订单*/
    @GetMapping("/getAllOldOrderBySid")
    public String getAllOldOrderBySid(String sid,String currentPage,String pageSize){
        HashMap<String, Object> hashMap = orderService.getAllOldOrderBySid(sid, currentPage, Integer.valueOf(pageSize));
        return JSON.toJSONString(hashMap, SerializerFeature.WriteMapNullValue);
    }

    /*商家接收订单*/
    @GetMapping("/shopPickOrder")
    public String shopPickOrder(String oid){
        boolean flag = orderService.shopPickOrder(oid);
        if(flag){
            return "success";
        }
        return "fail";
    }

    /*骑手接受订单*/
    @GetMapping("/pickOrderByDid")
    public String pickOrderByDid(String oid,String did){
        boolean flag = orderService.pickOrderByDid(oid, did);
        if(flag){
            return "success";
        }
        return "fail";
    }

    /*获取骑手所接的单*/
    @GetMapping("/getAllOrderByDid")
    public String getAllOrderByDid(String did){
        List<OrderFreeVO> orders = orderService.getAllOrderByDid(did);
        return JSON.toJSONString(orders,SerializerFeature.WriteMapNullValue);
    }

    /*根据oid获取订单信息*/
    @GetMapping("/getOrderByOid")
    public String getOrderByOid(String oid){
        Order order = orderService.getOrderByOid(oid);
        return JSON.toJSONString(order,SerializerFeature.WriteMapNullValue);
    }

    /*骑手确认订单送达*/
    @GetMapping("/deliverySuccess")
    public String deliverySuccess(String oid) {
        System.out.println("oid:"+oid);
        boolean flag = orderService.deliverySuccess(oid);
        if(flag){
            return "success";
        }
        return "fail";
    }

}
