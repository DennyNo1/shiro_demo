package com.denny.shiro_demo.realm;

import com.denny.shiro_demo.entity.Account;
import com.denny.shiro_demo.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Denny
 * @Date 2024/9/25 11:07
 * @Description
 * @Version 1.0
 */
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        
        //获取当前登录的用户信息
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();



        //设置角色
        Set<String> roles=new HashSet<>();
        roles.add(account.getRole());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);

        //设置权限
        simpleAuthorizationInfo.addStringPermission(account.getPerms());

        return simpleAuthorizationInfo;


    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;//这个类，包含了客户端传来的用户名和密码
        Account account = accountService.findByUsername(token.getUsername());//数据库查出的该用户的记录的对象
        if(account!=null)
        {
            return new SimpleAuthenticationInfo(account,account.getPassword(),getName());
        }//getName()是为了获取当前realm的名称
        return null;
    }
}
