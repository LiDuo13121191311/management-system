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
public class Address {
    public int aId;
    private String areaCode;
    private String name;
    private String tel;
    private String address;
    private String province;
    private String city;
    private String county;

}
