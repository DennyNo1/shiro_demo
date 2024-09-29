package com.denny.shiro_demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.denny.shiro_demo.entity.Account;
import com.denny.shiro_demo.mapper.AccountMapper;
import com.denny.shiro_demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Denny
 * @Date 2024/9/25 10:58
 * @Description
 * @Version 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account findByUsername(String username) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("username",username);
        return accountMapper.selectOne(wrapper);
    }
}
