package com.denny.shiro_demo.service;

import com.denny.shiro_demo.entity.Account;

/**
 * @Author Denny
 * @Date 2024/9/25 10:55
 * @Description
 * @Version 1.0
 */
public interface AccountService {
    public Account findByUsername(String username);
}
