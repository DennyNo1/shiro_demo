package com.denny.shiro_demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author Denny
 * @Date 2024/9/28 10:52
 * @Description
 * @Version 1.0
 */
@SpringBootTest
class AccountServiceTest {
    @Autowired
    //当你在一个接口上使用 @Autowired，比如 private AccountService accountService;，Spring 将会查找一个实现了 AccountService 接口的 Bean，并且将其注入到这个字段中
    private AccountService accountService;
    @Test
    void findByUsername(){
        System.out.println(accountService.findByUsername("d"));
    }

}
