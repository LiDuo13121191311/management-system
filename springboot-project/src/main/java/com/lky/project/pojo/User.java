package com.lky.project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String phone;
    private String username;
    private String password;
    private Integer rid;
    private String picSrc;
    private String score;
    private String stat;
    private Integer sid;

}
