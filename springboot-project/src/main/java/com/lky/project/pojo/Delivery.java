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
public class Delivery {
    private Integer did;
    private String name;
    private String phone;
    /*总接单数*/
    private String totalNum;
    private String number;
    private Integer stat;
}
