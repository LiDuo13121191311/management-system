package com.lky.project.VO;

import com.lky.project.pojo.User;
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
public class UsersVO {
    private List<User> users;
    private Integer count;
}
