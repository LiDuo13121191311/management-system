package com.lky.project.mapper;

import com.lky.project.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface UserMapper {
        /*根据用户名获取信息*/
        User findUserByUsername(String username);

        /*根据uid获取用户*/
        User findUserByUid(Integer uid);

        /*根据用户名获取店家信息*/
        User findShopManagerByUsername(String username);

        /*根据用户名获取管理员信息*/
        User findManagerByUsername(String username);

        /*获取用户总数*/
        Integer getUserCount();

        /*分页获取所有用户*/
        List<User> findAllUserByPage(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

        /*根据用户手机号查找用户是否存在*/
        User findUserByPhone(String phone);

        /*用户注册*/
        Integer userRegister(User user);

        /*用户增加下单量*/
        Integer addScore(String oid);

        /*修改用户信息*/
        Integer updateUserMessage(User user);
}
