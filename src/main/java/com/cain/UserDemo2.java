package com.cain;

import com.cain.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class UserDemo2 {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService2 userService2 = applicationContext.getBean(UserService2.class);
        userService2.transfer4();
    }
}
