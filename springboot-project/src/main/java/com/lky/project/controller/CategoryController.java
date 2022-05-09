package com.lky.project.controller;

import com.alibaba.fastjson.JSON;
import com.lky.project.pojo.Category;
import com.lky.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    /*获取所有类别*/
    @GetMapping("/getAllCategory")
    public String getAllCategory(){
        List<Category> allCategory = categoryService.getAllCategory();
        if(allCategory!=null){
            return JSON.toJSONString(allCategory);
        }
        return "none";
    }

    /*分页获取类别*/
    @GetMapping("/findCategoriesByPage")
    public String findCategoriesByPage(String uid,String currentPage,String pageSize){
        HashMap<String, Object> hashMap = categoryService.findCategoriesByPage(uid, currentPage, Integer.parseInt(pageSize));
        return JSON.toJSONString(hashMap);
    }

    /*根据sid获取对应的商品类别*/
    @GetMapping("/getAllCategoryBySid")
    public String getAllCategoryBySid(String sid){
        List<Category> categories = categoryService.getAllCategoryBySid(sid);
        return JSON.toJSONString(categories);
    }

    /*查询类别名是否存在*/
    @GetMapping("/findCategoryNameBySid")
    public String findCategoryNameBySid(String categoryName,String sid){
        Category category = categoryService.findCategoryNameBySid(categoryName, sid);
        if(category!=null){
            return "exist";
        }
        return "none";
    }

    /*保存修改后的类别*/
    @PostMapping("/updateCategoryMessage")
    public String updateCategoryMessage(@RequestBody Category category){
        boolean flag = categoryService.updateCategoryMessage(category);
        if(flag){
            return "success";
        }
        return "fail";
    }

    /*新增类别*/
    @PostMapping("/addCategoryMessage")
    public String addCategoryMessage(@RequestBody Category category){
        boolean flag = categoryService.addCategoryMessage(category);
        if(flag){
            return "success";
        }
        return "fail";
    }

    /*保存修改后的类别*/
    @PostMapping("/deleteCategory")
    public String deleteCategory(Integer cid){
        boolean flag = categoryService.deleteCategory(cid);
        if(flag){
            return "success";
        }
        return "fail";
    }




}
