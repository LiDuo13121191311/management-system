package com.lky.project.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopsVO {
    private List<ShopManagerRegisterVO> shopManagerRegisterVOS;
    private Integer count;
}
