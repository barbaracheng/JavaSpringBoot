package com.sly.service.impl;

import com.sly.entities.Account;
import com.sly.mapper.LoginMapper;
import com.sly.mapper.impl.LoginMapperImpl;
import com.sly.service.LoginService;

import java.util.Objects;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/21 17:11
 */
public class LoginServiceImpl implements LoginService {
    private LoginMapper loginMapper = new LoginMapperImpl();
    /**
     * 用户登录的业务逻辑
     *
     * @param userName 用户名
     * @param userPwd  密码
     * @return 用户信息
     */
    @Override
    public Account login(String userName, String userPwd) {
        Account account = loginMapper.login(userName);
        // 条件成立：表示没有找到对应的用户
        if (null == account) {
            return null;
        }
        // 判断客户输入的密码是否等于数据库表查询的密码,条件成立返回客户信息
        if(Objects.equals(userPwd,account.getUserPwd())) {
            return account;
        } else {
            return null;
        }
    }
}
