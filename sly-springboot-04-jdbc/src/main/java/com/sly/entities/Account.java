package com.sly.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/21 17:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Integer id;

    private String userName;

    private String userPwd;
}
