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
public class OrderGood {
    private Integer id;
    public Integer oid;
    private Integer gid;
    private Integer count;
}
