package com.lky.project.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodInfoVO {
    private Integer gid;
    private String goodName;
    private String goodPic;
    private String price;
    private Integer sales;
    private String desc;
    private String category;
    private Integer sid;
    private Integer cid;
}
