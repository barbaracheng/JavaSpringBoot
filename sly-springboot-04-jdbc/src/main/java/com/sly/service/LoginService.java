package com.sly.service;

import com.sly.entities.Account;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/21 17:10
 */
public interface LoginService {
    /**
     * 用户登录的业务逻辑
     * @param userName 用户名
     * @param userPwd 密码
     * @return 用户信息
     */
    Account login(String userName,String userPwd);
}
