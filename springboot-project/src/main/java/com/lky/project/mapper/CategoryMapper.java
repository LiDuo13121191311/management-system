package com.lky.project.mapper;

import com.lky.project.pojo.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    /*根据cid获取类别*/
    Category findCategoryByCid(Integer cid);

    /*获取所有类别*/
    List<Category> findAllCategory();

    /*获取对应sid类别数量*/
    Integer getCategoryCountBySid(Integer sid);

    /*分页获取类别*/
    List<Category> findCategoriesByPage(@Param("sid") Integer sid,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);

    /*查找类别名是否存在*/
    Category findCategoryNameBySid(String categoryName, String sid);

    /*保存修改后的类别*/
    Integer updateCategoryMessage(Category category);

    /*新增类别*/
    Integer addCategoryMessage(Category category);

    /*根据sid获取对应的商品类别*/
    List<Category> getAllCategoryBySid(String sid);

    Integer deleteCategory(Integer cid);
}
