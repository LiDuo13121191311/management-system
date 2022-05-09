package com.lky.project.mapper;

import com.lky.project.pojo.Good;
import com.lky.project.pojo.Order;
import com.lky.project.pojo.OrderGood;
import com.lky.project.pojo.User;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface OrderMapper {

    /*存储order信息*/
    Integer addOrder(Order order);

    /*根据订单号查询订单*/
    Order findOrderByOrderNumber(String orderNumber);

    /*存储orderGood信息*/
    Integer addOrderGood(OrderGood orderGood);

    /*根据uid获取所有的order*/
    List<Order> findAllOrdersByUid(Integer uid);

    /*根据oid获取该订单所有的goodId*/
    List<OrderGood> findAllGoodsByOid(Integer oid);

    /*根据oid和gid查询对应的order_good记录的count*/
    OrderGood findCountByOidAndGid(Integer oid, Integer gid);

    /*获取所有待骑手接单（orderStat为2）的订单*/
    List<Order> findOrderByOrderStat(Integer orderStat);

    /*获取sid的所有待接订单的数量*/
    Integer getOrderCountBySidAndStat(@Param("sid") String sid,@Param("orderStat") Integer orderStat);

    /*分页获取sid对应的新订单*/
    List<Order> findAllOrderBySidAndStat(@Param("sid") String sid,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize,@Param("orderStat") Integer orderStat);


    /*获取sid的所有已接订单的数量*/
    Integer getOldOrderCountBySid(String sid);

    /*分页获取sid对应的旧订单*/
    List<Order> findAllOldOrderBySid(@Param("sid") String sid,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);

    /*商家接受订单*/
    Integer shopPickOrder(@Param("oid") String oid,@Param("orderTime") String orderTime);

    /*骑手接受订单*/
    Integer pickOrderByDid(@Param("oid") String oid, @Param("did") String did,@Param("pickTime") String pickTime);

    /*获取骑手所接的单*/
    List<Order> findAllOrdersByDid(String did);

    /*根据oid获取订单信息*/
    Order findOrderByOid(String oid);

    /*骑手确认订单送达*/
    Integer deliverySuccess(@Param("oid") String oid,@Param("arriveTime") String arriveTime);
}
