package com.lee.controller;

import com.lee.Entity.User;
import com.lee.utils.JwtUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/login")
    public String login(User user) {
//    System.out.println(user.getUsername());
//    System.out.println(user.getPassword());
//    System.out.println("-----------------------");
//        if (user.getUsername().equals("admin") && user.getPassword().equals("admin")) {
//            User u = new User();
//            u.setUserid(123);
//            u.setPassword("admin");
//            u.setUsername("admin");
//            u.setToken(JwtUtils.getToken(u));
//      System.out.println(JwtUtils.getToken(u));
//            return JwtUtils.getToken(u);
//        }
//        return "登录失败！账号或者密码不对！";
        String sign = JwtUtils.sign(user.getUsername(), user.getUserid().toString());
        return sign;
    }

    @GetMapping("/test")
    public String test(HttpServletRequest httpServletRequest)  {
        String token= httpServletRequest.getHeader("token");
        //System.out.println(token);
        boolean verify = false;
        try {
            verify = JwtUtils.verity(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(verify){
            return "权限内";
        }
        return "error";

    }
    @GetMapping("/info")
    public  User getInfo(HttpServletRequest httpServletRequest){
        String token= httpServletRequest.getHeader("token");
        return JwtUtils.getInfo(token);
    }
}
