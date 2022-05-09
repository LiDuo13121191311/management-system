package com.lky.project.mapper;

import com.lky.project.VO.GoodInfoVO;
import com.lky.project.pojo.Good;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface GoodMapper {
    /*根据gid获取good*/
    Good findGoodByGid(Integer gid);

    /*根据商品名模糊查询*/
    List<Good> findGoodByNameLike(String value);

    /*获取对应店铺的商品总数*/
    Integer findGoodCountBySid(Integer sid);

    /*分页获取商品*/
    List<GoodInfoVO> findGoodByPage(@Param("sid") Integer sid, @Param("startIndex") int startIndex,@Param("pageSize") int pageSize);

    /*修改店铺信息*/
    Integer updateGoodMessage(GoodInfoVO goodInfoVO);
    Integer deleteGood(Integer gid);

    /*根据oid获取对应的商品*/
    List<Good> findGoodByOid(Integer oid);

    /*添加新的商品*/
    Integer addGoodMessage(Good good);

    /*根据sid获取good*/
    List<Good> findAllGoodBySid(String sid);

    /*增加销量*/
    Integer addGoodSales(@Param("gid") Integer gid,@Param("count") Integer count);
}
