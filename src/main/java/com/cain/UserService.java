package com.cain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

//@Component
public class UserService {
//    @Autowired
    JdbcTemplate jdbcTemplate;
//    @Autowired
    PlatformTransactionManager transactionManager;
//    @Autowired
    TransactionTemplate transactionTemplate;

    public void transfer(){
        // 1.设置transaction规则
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        // 2.获取transaction
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            // 3.进行事务逻辑
            jdbcTemplate.update("update user set money = ? where username = ?",1000,"cain");
            // 3.1 成功则提交事务
            transactionManager.commit(transactionStatus);
        } catch (DataAccessException e) {
            // 3.2 失败则回滚事务
            transactionManager.rollback(transactionStatus);
            throw new RuntimeException(e);
        }
    }

    public void transfer2(){
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    // 进行事务逻辑
                    jdbcTemplate.update("update user set money = ? where username = ?",2000,"cain");
                    int i = 1/0;
                } catch (DataAccessException e) {
                    // 3.2 失败则回滚事务
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
