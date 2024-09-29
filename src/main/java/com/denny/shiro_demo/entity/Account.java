package com.denny.shiro_demo.entity;

import lombok.Data;

/**
 * @Author Denny
 * @Date 2024/9/15 19:30
 * @Description
 * @Version 1.0
 */
@Data
public class Account {
    private Integer id;
    private String username;
    private String password;
    private String perms;
    private String role;
}
