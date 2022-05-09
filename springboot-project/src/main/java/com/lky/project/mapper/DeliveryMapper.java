package com.lky.project.mapper;

import com.lky.project.pojo.Delivery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface DeliveryMapper {

    /*根据oid获取taker*/
    Delivery findTakerByOid(Integer oid);

    /*根据phone查询rider*/
    Delivery findDeliveryByPhone(String phone);


    /*获取骑手总数*/
    Integer getRidersCount();

    /*分页获取骑手信息*/
    List<Delivery> findAllRidersByPage(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /*根据did查找骑手信息*/
    Delivery findDeliveryByDid(Integer did);

    /*根据oid获取did，增加骑手接单量*/
    Integer addTotalNum(String oid);
}
