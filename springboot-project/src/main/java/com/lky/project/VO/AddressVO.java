package com.lky.project.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressVO {
    private String areaCode;
    private String name;
    private String tel;
    private String addressDetail;
    private String province;
    private String city;
    private String county;
    private String uid;
}
