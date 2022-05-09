package com.lky.project.service;

import com.lky.project.pojo.Address;
import com.lky.project.VO.AddressVO;

import java.util.List;

public interface AddressService {
    /*根据用户id查询所有address*/
    List<Address> listAllAddress(Integer id);

    /*根据用户id修改address*/
    boolean updateAddressById(Address address);

    /*添加address*/
    boolean addAddress(AddressVO address);
}
