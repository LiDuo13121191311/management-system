package com.lky.project.service.impl;

import com.lky.project.mapper.DeliveryMapper;
import com.lky.project.pojo.Delivery;
import com.lky.project.service.DeliveryService;
import com.lky.project.utils.PageTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Resource
    DeliveryMapper deliveryMapper;


    /*根据oid获取taker*/
    @Override
    public Delivery findTakerByOid(Integer oid) {
        return deliveryMapper.findTakerByOid(oid);
    }

    /*根据phone查询rider*/
    @Override
    public Delivery findDeliveryByPhone(String phone) {
        return deliveryMapper.findDeliveryByPhone(phone);
    }

    /*分页查询所有骑手*/
    @Override
    public HashMap<String,Object> findAllRidersByPage(String currentPage, Integer pageSize) {
        //查询总骑手数
        Integer count = deliveryMapper.getRidersCount();
        PageTool pageTool = new PageTool(count, currentPage, pageSize);
        List<Delivery> deliveries = deliveryMapper.findAllRidersByPage(pageTool.getStartIndex(), pageTool.getPageSize());
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("deliveries",deliveries);
        hashMap.put("count",count);
        return hashMap;
    }

    /*根据did获取delivery*/
    @Override
    public Delivery findDeliveryByDid(Integer did) {
        return deliveryMapper.findDeliveryByDid(did);
    }
}
