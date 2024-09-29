package com.denny.shiro_demo.config;

import com.denny.shiro_demo.realm.AccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Denny
 * @Date 2024/9/25 11:18
 * @Description
 * @Version 1.0
 */

@Configuration
public class ShiroConfig {
    @Bean//为了让方法返回一个对象，该对象在容器中注册成bean。
    //而@Service, @Repository, @Controller，@Component是让一个类并将其注册为 Spring 容器中的一个 Bean
    //这意味着Spring会在启动时自动创建这个类的一个或多个实例（取决于配置），并将这些实例作为Bean注册到Spring的IoC容器中。
    public AccountRealm accountRealm(){
        return  new AccountRealm();
    }


    //在 @Configuration 类中，当一个 @Bean 方法需要依赖其他 Bean 时，你可以直接将这些依赖作为方法参数传递给该方法。Spring 会自动解析这些依赖并将它们注入到相应的位置。
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("accountRealm") AccountRealm accountRealm)//@Qualifier为了显示指定bean的名称
    {
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(accountRealm);
        return defaultWebSecurityManager;
    }

    @Bean public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //权限设置
        Map<String,String> map=new HashMap<>();
        map.put("/main","authc");
        map.put("/manage","perms[manager]");
        map.put("/admin","roles[admin]");
        map.put("/login", "anon");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/login");//设置登录路径
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");

        // 打印日志确认 ShiroFilterFactoryBean 正常加载
        System.out.println("ShiroFilterFactoryBean initialized");


        return shiroFilterFactoryBean;
    }
}
