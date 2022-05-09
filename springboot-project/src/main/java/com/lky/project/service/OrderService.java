package com.lky.project.service;

import com.lky.project.VO.NewOrderInfoVO;
import com.lky.project.VO.OrderFreeVO;
import com.lky.project.VO.OrderInfoVO;
import com.lky.project.VO.OrderVO;
import com.lky.project.pojo.Order;

import java.util.HashMap;
import java.util.List;

public interface OrderService {
    /*创建订单*/
    Order createOrder(OrderVO orderVO);

    /*根据uid获取到所有信息*/
    List<OrderInfoVO> findAllOrderByUid(Integer uid);

    /*获取所有待接单的订单*/
    List<OrderFreeVO> getFreeOrders();

    /*获取所有待商家接单的订单*/
    HashMap<String,Object> getAllOrderBySidAndStat(String sid, String currentPage, Integer pageSize,Integer orderStat);

    /*根据sid获取所有已接订单*/
    HashMap<String,Object> getAllOldOrderBySid(String sid, String currentPage, Integer pageSize);

    /*商家接受订单*/
    boolean shopPickOrder(String oid);

    /*骑手接受订单*/
    boolean pickOrderByDid(String oid,String did);

    /*获取骑手所接的单*/
    List<OrderFreeVO> getAllOrderByDid(String did);

    /*根据oid获取订单信息*/
    Order getOrderByOid(String oid);

    /*骑手确认订单送达*/
    boolean deliverySuccess(String oid);
}
