package com.lky.project.mapper;

import com.lky.project.VO.AddressVO;
import com.lky.project.pojo.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface AddressMapper {

    /*根据用户id查询所有address*/
    List<Address> listAllAddress(Integer id);

    /*根据用户id修改address*/
    Integer updateAddressById(Address address);

    /*添加address*/
    Integer addAddress(AddressVO address);

    /*根据aid获取address*/
    Address findAddressByAid(Integer aid);
}


