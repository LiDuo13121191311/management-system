package com.lky.project.mapper;

import com.lky.project.pojo.Category;
import com.lky.project.pojo.Good;
import com.lky.project.pojo.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface ShopMapper {
    /*查询所有店铺*/
    List<Shop> findAllShop();

    /*根据sid获取所有category*/
    List<Category> findAllCategoryBySid(String sid);

    /*根据cid获取所有的good*/
    List<Good> findAllGoodByCid(Integer cid);

    /*根据sid获取到具体的店铺信息*/
    Shop findShopBySid(Integer sid);

    /*根据店铺license查询店铺是否存在*/
    Shop findShopByLicense(String license);

    /*新增店铺*/
    Integer addShop(Shop shop);

    /*根据uid获取shop*/
    Shop findShopByUid(String uid);

    /*获得店铺（在开和冻结）总数*/
    Integer getShopsCount();

    /*分页查询所有店铺*/
    List<Shop> findAllShopByPage(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /*获取待办理的店铺申请总数*/
    Integer getShopApplyForCount();

    /*获取待审核的店铺*/
    List<Shop> findAllShopApplyForByPage(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /*保存修改的店铺信息*/
    Integer saveShopInfo(Shop shop);

    /*修改店铺状态*/
    Integer changeShopStat(@Param("stat") String stat,@Param("sid") String sid);

    /*同意店铺申请*/
    Integer agreeShopApplyFor(String sid);

    /*根据店铺名模糊查询*/
    List<Shop> findShopsByShopNameLike(String value);

    /*修改店铺信息*/
    Integer updateShopMessage(Shop shop);

    /*根据销售量降序获取店铺*/
    List<Shop> findShopOrderBySales();

    /*给店铺增加销量*/
    Integer addTotalSales(String oid);
}
