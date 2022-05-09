package com.lky.project.VO;

import com.lky.project.pojo.Good;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodVO {
    private String categoryName;
    private List<Good> goods;
}
