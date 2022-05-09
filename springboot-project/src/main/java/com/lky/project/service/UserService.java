package com.lky.project.service;

import com.lky.project.VO.UsersVO;
import com.lky.project.pojo.User;

import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator
 */
public interface UserService {
    /*根据用户名获取用户信息*/
    User findUserByUsername(String username);

    /*根据用户名获取店家信息*/
    User findShopManagerByUsername(String username);

    /*根据用户名获取用户信息*/
    User findManagerByUsername(String username);

    /*分页获取所有用户*/
    HashMap<String,Object> findAllUserByPage(String currentPage, Integer pageSize);

    /*根据用户手机号查找用户是否存在*/
    User findUserByPhone(String phone);

    /*用户注册*/
    boolean userRegister(User user);

    /*根据id获取用户*/
    User findUserByUid(String id);

    /*修改用户信息*/
    boolean updateUserMessage(User user);
}
