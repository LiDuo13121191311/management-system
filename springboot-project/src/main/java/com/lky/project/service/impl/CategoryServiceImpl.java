package com.lky.project.service.impl;

import com.lky.project.mapper.CategoryMapper;
import com.lky.project.mapper.ShopMapper;
import com.lky.project.pojo.Category;
import com.lky.project.pojo.Shop;
import com.lky.project.service.CategoryService;
import com.lky.project.utils.PageTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;

    @Resource
    ShopMapper shopMapper;


    /*获取所有类别*/
    @Override
    public List<Category> getAllCategory() {
        return categoryMapper.findAllCategory();
    }


    /*分页获取类别*/
    @Override
    public HashMap<String, Object> findCategoriesByPage(String uid, String currentPage, int pageSize) {
        Shop shop = shopMapper.findShopByUid(uid);
        Integer count = categoryMapper.getCategoryCountBySid(shop.getSid());
        PageTool pageTool = new PageTool(count, currentPage, pageSize);
        List<Category> categories = categoryMapper.findCategoriesByPage(shop.getSid(), pageTool.getStartIndex(), pageTool.getPageSize());
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("categories",categories);
        hashMap.put("count",count);
        return hashMap;
    }

    /**/

    /*查找类别名是否存在*/
    @Override
    public Category findCategoryNameBySid(String categoryName, String sid) {
        return categoryMapper.findCategoryNameBySid(categoryName,sid);
    }

    /*保存修改后的类别*/
    @Override
    public boolean updateCategoryMessage(Category category) {
        Integer row = categoryMapper.updateCategoryMessage(category);
        if(row>0){
            return true;
        }
        return false;

    }

    /*新增类别*/
    @Override
    public boolean addCategoryMessage(Category category) {
        Integer row = categoryMapper.addCategoryMessage(category);
        if(row>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCategory(Integer cid) {
        Integer row = categoryMapper.deleteCategory(cid);
        if(row>0){
            return true;
        }
        return false;
    }

    /*根据sid获取对应的商品类别*/
    @Override
    public List<Category> getAllCategoryBySid(String sid) {
        return categoryMapper.getAllCategoryBySid(sid);
    }
}
