package com.lky.project.service.impl;

import com.lky.project.VO.NewOrderInfoVO;
import com.lky.project.VO.OrderFreeVO;
import com.lky.project.VO.OrderInfoVO;
import com.lky.project.VO.OrderVO;
import com.lky.project.mapper.*;
import com.lky.project.pojo.*;
import com.lky.project.service.OrderService;
import com.lky.project.utils.DateUtil;
import com.lky.project.utils.PageTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Administrator
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private AddressMapper addressMapper;

    @Resource
    private GoodMapper goodMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    ShopMapper shopMapper;

    @Resource
    OrderGoodMapper orderGoodMapper;

    @Resource
    DeliveryMapper deliveryMapper;

    private DateUtil dateUtil = new DateUtil();


    /*创建订单*/
    @Override
    public Order createOrder(OrderVO orderVO) {
        //1、构建order实体类，存储订单表
        //1.1 存下该订单的订单号：DM + 时间戳 + 000 + 用户id
        String orderNumber = "DM" +dateUtil.getTimeStamp()+"000" + orderVO.getUid();

        Order order = new Order(null, orderVO.getUid(), orderVO.getSid(), orderVO.getAddressMessage().getAId(),null,
                orderNumber, orderVO.getGoodTotalPrice(),orderVO.getDeliveryPrice(),
                orderVO.getTotalPrice(), dateUtil.getNowTime(), null);
        System.out.println("order:"+order);
        //1.2 持久化订单信息
        Integer flag = orderMapper.addOrder(order);
        if(flag>0){
            //2、存储订单商品表
            //2.1 根据存储的订单号找到刚存储的order
            Order order1 = orderMapper.findOrderByOrderNumber(orderNumber);
            //2.2 遍历存储OrderGood表
            int count = 0;
            for (Good good : orderVO.getGoods()) {
                OrderGood orderGood = new OrderGood(null, order1.getOid(), good.getGid(), good.getCount());
                int row = orderMapper.addOrderGood(orderGood);
                count+=row;
            }
            System.out.println("成功存储"+count+"条订单商品信息");
            return order1;
        }
        return null;
    }

    /*根据uid获取所有的order*/
    @Override
    public List<OrderInfoVO> findAllOrderByUid(Integer uid) {
        /*前端所需数据：  oid:'',
                        sid:'',
                        logoSrc:'/pic/kfcLogo.png',
                        shopName:'华莱士·全鸡汉堡',
                        orderStat:1,
                        goodTotalPrice,
                        totalPrice:'50',
                        createTime:'2021.8.9',
                        addressMessage:'',
                        goods:'',
                        orderNumber:'',
                        deliveryPrice:''
                        dmId:''
                        */
        //1、根据uid获取所有的order
        /*可以获取的数据：  oid:'',
                          sid:'',
                            aid:'',
                        orderStat:1,
                        totalPrice:'50',
                        createTime:'2021.8.9',
                        goodTotalPrice:'',
                        orderNumber:'',
                        deliveryPrice:''*/
        List<OrderInfoVO> orderInfoVOS = new ArrayList<>();
        List<Order> orders = orderMapper.findAllOrdersByUid(uid);
        System.out.println("orders:"+orders);
        for (Order order : orders) {
            //2、根据aid获取到具体的地址信息
            Address address = addressMapper.findAddressByAid(order.getAid());

            //3、根据sid获取到具体的店铺信息
            Shop shop = shopMapper.findShopBySid(order.getSid());

            //4、根据oid获取该订单所有的good
            List<OrderGood> orderGoods = orderMapper.findAllGoodsByOid(order.getOid());
            List<Good> goods = new ArrayList<>();
            for (OrderGood orderGood : orderGoods) {
                //给good获取对应的count
                Good good = goodMapper.findGoodByGid(orderGood.getGid());
                good.setCount(orderGood.getCount());
                goods.add(good);
            }
            //5、OrderInfoVO对象数据组合
            OrderInfoVO orderInfoVO = new OrderInfoVO(order.getOid(), shop.getSid(), shop.getLogoSrc(), shop.getShopName(),
                    order.getOrderStat(), order.getGoodTotalPrice(), order.getDeliveryPrice(),
                    order.getTotalPrice(), order.getCreateTime(), goods, address, order.getOrderNumber());
            orderInfoVOS.add(orderInfoVO);
        }
        System.out.println(orderInfoVOS);
        return orderInfoVOS;
    }

    /*获取所有待接单的订单*/
    @Override
    public List<OrderFreeVO> getFreeOrders() {
        List<OrderFreeVO> list = new ArrayList<>();

        //1、获取所有状态为2的订单（1：已支付，待商家接单；2：商家已接单，待骑手接单；3：骑手已接单，待送达；4、已送达）
        List<Order> orders = orderMapper.findOrderByOrderStat(2);

        for (Order order : orders) {
            //2、根据每个订单里的sid获取店铺信息
            Shop shop = shopMapper.findShopBySid(order.getSid());
            //3、根据aid获取地址信息
            Address address = addressMapper.findAddressByAid(order.getAid());
            //4、根据uid获取userName和phone
            User user = userMapper.findUserByUid(order.getUid());
            OrderFreeVO orderFreeVO = new OrderFreeVO(user.getUsername(), user.getPhone(), order, shop, address);
            list.add(orderFreeVO);
        }
        return list;
    }

    /*获取所有待商家接单的订单*/
    @Override
    public HashMap<String, Object> getAllOrderBySidAndStat(String sid, String currentPage, Integer pageSize,Integer orderStat) {
        List<NewOrderInfoVO> list = new ArrayList<>();
        //获取sid的所有待接订单的数量
        Integer count = orderMapper.getOrderCountBySidAndStat(sid,orderStat);
        PageTool pageTool = new PageTool(count, currentPage, pageSize);
        //根据sid获取到所有的订单,分页查询
        List<Order> orders = orderMapper.findAllOrderBySidAndStat(sid, pageTool.getStartIndex(), pageTool.getPageSize(),orderStat);
        for (Order order : orders) {
            //根据uid获取用户信息
            User userInfo = userMapper.findUserByUid(order.getUid());
            //根据aid获取收货地址
            Address addressInfo = addressMapper.findAddressByAid(order.getAid());
            //根据oid获取订单商品等信息
            //获取对应的goodId
            List<OrderGood> orderGoods = orderGoodMapper.findGidByOid(order.getOid());
            List<Good> goodInfo = new ArrayList<>();
            for (OrderGood orderGood : orderGoods) {
                Good good = goodMapper.findGoodByGid(orderGood.getGid());
                good.setCount(orderGood.getCount());
                goodInfo.add(good);
            }
            //获取骑士信息
            Delivery delivery = null;
            if(order.getDid()!=null){
                delivery = deliveryMapper.findDeliveryByDid(order.getDid());
            }
            //封装数据
            NewOrderInfoVO newOrderInfoVO = new NewOrderInfoVO(userInfo, order,goodInfo, addressInfo,delivery);
            list.add(newOrderInfoVO);
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("orders",list);
        hashMap.put("totalCount",count);
        return hashMap;

    }

    /*根据sid获取所有已接订单*/
    @Override
    public HashMap<String, Object> getAllOldOrderBySid(String sid, String currentPage, Integer pageSize) {
        List<NewOrderInfoVO> list = new ArrayList<>();
        //获取sid的所有待接订单的数量
        Integer count = orderMapper.getOldOrderCountBySid(sid);
        PageTool pageTool = new PageTool(count, currentPage, pageSize);
        //根据sid获取到所有的订单,分页查询
        List<Order> orders = orderMapper.findAllOldOrderBySid(sid, pageTool.getStartIndex(), pageTool.getPageSize());
        for (Order order : orders) {
            //根据uid获取用户信息
            User userInfo = userMapper.findUserByUid(order.getUid());
            //根据aid获取收货地址
            Address addressInfo = addressMapper.findAddressByAid(order.getAid());
            //根据oid获取订单商品等信息
            //获取对应的goodId
            List<OrderGood> orderGoods = orderGoodMapper.findGidByOid(order.getOid());
            List<Good> goodInfo = new ArrayList<>();
            for (OrderGood orderGood : orderGoods) {
                Good good = goodMapper.findGoodByGid(orderGood.getGid());
                good.setCount(orderGood.getCount());
                goodInfo.add(good);
            }
            //获取骑士信息
            Delivery delivery = null;
            if(order.getDid()!=null){
                delivery = deliveryMapper.findDeliveryByDid(order.getDid());
            }
            //封装数据
            NewOrderInfoVO newOrderInfoVO = new NewOrderInfoVO(userInfo, order,goodInfo, addressInfo,delivery);
            list.add(newOrderInfoVO);
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("orders",list);
        hashMap.put("totalCount",count);
        return hashMap;
    }

    /*商家接受订单*/
    @Override
    public boolean shopPickOrder(String oid) {
        Integer row = orderMapper.shopPickOrder(oid,dateUtil.getNowTime());
        if(row>0){
            return true;
        }
        return false;

    }

    /*骑手接受订单*/
    @Override
    public boolean pickOrderByDid(String oid, String did) {
        Integer row = orderMapper.pickOrderByDid(oid, did, dateUtil.getNowTime());
        if(row>0){
            return true;
        }
        return false;
    }

    /*获取骑手所接的单*/
    @Override
    public List<OrderFreeVO> getAllOrderByDid(String did) {
        List<OrderFreeVO> list = new ArrayList<>();
        //根据did获取所接的订单
        List<Order> orders = orderMapper.findAllOrdersByDid(did);
        //每个订单
        for (Order order : orders) {
            //获取用户信息
            User user = userMapper.findUserByUid(order.getUid());

            //获取地址信息
            Address address = addressMapper.findAddressByAid(order.getAid());

            //获取店铺信息
            Shop shop = shopMapper.findShopBySid(order.getSid());
            OrderFreeVO orderFreeVO = new OrderFreeVO(user.getUsername(), user.getPhone(), order, shop, address);
            list.add(orderFreeVO);
        }
        return list;


    }


    /*根据oid获取订单信息*/
    @Override
    public Order getOrderByOid(String oid) {
        Order order = orderMapper.findOrderByOid(oid);
        return order;
    }

    /*骑手确认订单送达*/
    @Override
    public boolean deliverySuccess(String oid) {
        //改变订单状态
        Integer row = orderMapper.deliverySuccess(oid, dateUtil.getNowTime());
        //获取到订单
        List<OrderGood> orderGoods = orderGoodMapper.findGidByOid(Integer.valueOf(oid));
        //将订单里的商品的数量存入对应商品的销量里
        for (OrderGood orderGood : orderGoods) {
            //商品增加销量
            goodMapper.addGoodSales(orderGood.getGid(),orderGood.getCount());
        }
        //店铺增加销量
        shopMapper.addTotalSales(oid);
        //骑手增加接单量
        deliveryMapper.addTotalNum(oid);
        //用户增加下单量
        userMapper.addScore(oid);
        System.out.println("row:"+row);
        if(row>0){
            return true;
        }
        return false;
    }


}
