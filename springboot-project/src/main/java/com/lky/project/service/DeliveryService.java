package com.lky.project.service;

import com.lky.project.pojo.Delivery;

import java.util.HashMap;
import java.util.List;

public interface DeliveryService {
    /*根据oid获取taker*/
    Delivery findTakerByOid(Integer oid);

    /*根据phone查询rider*/
    Delivery findDeliveryByPhone(String phone);

    /*分页查询所有骑手*/
    HashMap<String,Object> findAllRidersByPage(String currentPage, Integer valueOf);

    /*根据did获取delivery*/
    Delivery findDeliveryByDid(Integer did);
}
