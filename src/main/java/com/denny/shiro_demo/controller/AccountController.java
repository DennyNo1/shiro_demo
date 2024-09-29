package com.denny.shiro_demo.controller;

import com.denny.shiro_demo.entity.Account;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Denny
 * @Date 2024/9/25 11:47
 * @Description
 * @Version 1.0
 */
@Controller
public class AccountController {

    @GetMapping("/{url}")

    public String redirect(@PathVariable("url") String url){
        return url;//这个就是前后端不分离的写法。返回后，thymeleaf会自动去找对应的静态文件。
    }
    @PostMapping("/login")
    public String login(String username,String password, Model model)
    {
        Subject subject= SecurityUtils.getSubject();//代表当前用户
        //把username和password集合成一个token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);



        //流程会进入realm
        try {
            subject.login(token);

            //获取用户对象,把它存入session
            Account account = (Account) subject.getPrincipal();
            subject.getSession().setAttribute("account",account);
            return "index";//前端请求什么路径，后端返回什么路径。这里就是，登录成功后，返回/index
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            model.addAttribute("msg","用户名错误！");
            //在Spring MVC框架中，Model接口是用于将数据从控制器传递到视图的一个工具。它本质上是一个Map，可以用来存储键值对，这些键值对随后可以在视图模板（如JSP、Thymeleaf等）中使用。
            return "login";
        }
        catch (IncorrectCredentialsException e)
        {
            e.printStackTrace();
            model.addAttribute("msg","密码错误！");
            return "login";
        }


    }
    @GetMapping("/unauth")
    @ResponseBody
    public String unauth(){
        return "未授权，无法访问";
    }

    @GetMapping("/logout")

    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
