package com.myit.controller;


import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    //1.shiro登录失败后跳转到'/login'这个请求路径，在请求属性中设置了"shiroLoginFailure"属性，
    //          该属性是shiro登录失败异常信息,最后执行下面方法并将失败信息回传到登录页面。
    //2.shiro登录成功则自动跳转到上一个请求路径，不会执行下面的请求方法。如果上一个路径没有，则跳转到/目录
    //3.shiro登录成功,则把登录信息保存在Subject中，获得Subject方法：SecurityUtils.getSubject()
    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest req) {
        //如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        //根据shiro返回的异常类路径判断，抛出指定异常信息
        if (exceptionClassName != null) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                //最终会抛给异常处理器
                model.addAttribute("errorMsg", "账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(
                    exceptionClassName)) {
                model.addAttribute("errorMsg", "用户名/密码错误");
            } else {
                //最终在异常处理器生成未知错误.
                model.addAttribute("errorMsg", "其他异常信息");
            }
        }
        //此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
        //将登陆失败信息传到login页面
        return "forward:/login.jsp";
    }

}
