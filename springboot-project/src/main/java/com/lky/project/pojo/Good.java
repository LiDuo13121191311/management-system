package com.lky.project.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good {
    private Integer gid;
    private String goodName;
    private String goodPic;
    private String price;
    private Integer sales;
    private String desc;
    private Integer cid;
    private Integer sid;
    //配合前端结构，增加count属性，负责记录放入购物车的数量
    private int count;
}
