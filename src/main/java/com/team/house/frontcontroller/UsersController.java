package com.team.house.frontcontroller;

import com.team.house.entity.Users;
import com.team.house.service.UsersService;
import com.team.house.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("usersController2")
@RequestMapping("/page/")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("checkName")
    @ResponseBody
    public String checkName(String name){
        int i = usersService.checkUsername(name);
        return "{\"result\":"+i+"}";
    }
   //用户注册
    @RequestMapping("insertUser")
    public String insertUser(Users users){
        users.setIsadmin(0);
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        if (usersService.addUsers(users)){
            return "login";
        }else {
            return "regs";
        }
    }
    //用户登录
    @RequestMapping("loginUser")
    public String loginUser(String name, String password, Model model, HttpSession session){
        Users user = usersService.login(name, password);
        if(user==null){
            model.addAttribute("info","用户名和密码不正确");
            return "login";
        }else{
            session.setAttribute("loginInfo",user);
            //设置保存的有效时间
            session.setMaxInactiveInterval(300);  //以秒为单位
            return "redirect:showMoreHouse";   //用户登入后的管理页
        }
    }
}
