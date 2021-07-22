package com.sly.mapper.impl;

import com.sly.entities.Account;
import com.sly.jdbc.utils.DruidUtils;
import com.sly.jdbc.utils.XMLUtils;
import com.sly.mapper.LoginMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/21 17:05
 */
public class LoginMapperImpl implements LoginMapper {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtils.getDataSource());
    /**
     * 用户登录的Mapper
     * 根据用户名返回对应的用户信息
     *
     * @param userName 用户名
     * @return 返回对应的用户信息
     */
    @Override
    public Account login(String userName) {
        String sql = XMLUtils.getSQLByKey("login");
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class), userName);
    }
}
