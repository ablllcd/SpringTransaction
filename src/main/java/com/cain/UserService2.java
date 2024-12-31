package com.cain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOError;
import java.io.IOException;

@Component
public class UserService2 {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserService3 userService3;

    @Transactional
    public void transfer3(){
        jdbcTemplate.update("update user set money = ? where username = ?",1000,"cain");
        userService3.transfer();
//        int i = 1/0;
    }

    @Transactional(readOnly = true,timeout = 3000)
    public void transfer4() throws IOException {
        jdbcTemplate.update("update user set money = ? where username = ?",1000,"cain");
//        throw new IOException();
    }

}
