package com.sly.service;

import com.sly.entities.Account;
import com.sly.service.impl.LoginServiceImpl;
import org.junit.Test;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/21 17:14
 */
public class LoginServiceTest {
    private LoginService loginService = new LoginServiceImpl();
    @Test
    public void loginTest() {
        String userName = "admin";
        String userPwd = "admin";
        Account account = loginService.login(userName, userPwd);
        System.out.println(account);
    }
}
