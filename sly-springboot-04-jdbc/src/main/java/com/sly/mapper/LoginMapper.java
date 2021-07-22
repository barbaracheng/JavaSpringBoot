package com.sly.mapper;

import com.sly.entities.Account;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/21 17:02
 */
public interface LoginMapper {
    /**
     * 用户登录的Mapper
     * 根据用户名返回对应的用户信息
     * @param userName
     * @return
     */
    Account login(String userName);
}
