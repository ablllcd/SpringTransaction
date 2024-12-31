package com.cain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserService3 {
    @Autowired
    JdbcTemplate jdbcTemplate;

//    @Transactional
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Transactional(propagation = Propagation.NESTED)
//    @Transactional(propagation = Propagation.MANDATORY)
//    @Transactional(propagation = Propagation.SUPPORTS)
    @Transactional(propagation = Propagation.NEVER)
    public void transfer(){
        jdbcTemplate.update("update user set money = ? where username = ?",1000,"gala");
        int i = 1/0;
    }
}
