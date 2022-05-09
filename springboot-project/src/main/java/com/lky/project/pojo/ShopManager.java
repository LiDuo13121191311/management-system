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
public class ShopManager {
    private Integer smid;
    private String username;
    private String password;
    private String phone;
    private String name;
    private String identityNumber;
    private Integer stat;
    private Integer rid;
}
