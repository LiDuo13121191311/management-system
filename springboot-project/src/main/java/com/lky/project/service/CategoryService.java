package com.lky.project.service;

import com.lky.project.pojo.Category;

import java.util.HashMap;
import java.util.List;

public interface CategoryService {

    /*获取所有类别*/
    List<Category> getAllCategory();

    /*分页获取类别*/
    HashMap<String,Object> findCategoriesByPage(String uid, String currentPage, int pageSize);

    /*查找类别名是否存在*/
    Category findCategoryNameBySid(String categoryName, String sid);

    /*保存修改后的类别*/
    boolean updateCategoryMessage(Category category);

    /*新增类别*/
    boolean addCategoryMessage(Category category);
    boolean deleteCategory(Integer cid);

    /*根据sid获取对应的商品类别*/
    List<Category> getAllCategoryBySid(String sid);
}
