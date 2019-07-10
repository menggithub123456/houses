package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.entity.Users;
import com.team.house.service.UsersService;
import com.team.house.util.PageParam;
import com.team.house.util.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("getUsers")
    @ResponseBody
    public Map<String,Object> getUsers(UserParam userParam){
        PageInfo<Users> info = usersService.getUserList(userParam);
        Map<String,Object> map=new HashMap<>();
        map.put("total",info.getTotal());
        map.put("rows",info.getList());
        return map;
    }
    @RequestMapping("addUsers")
    @ResponseBody
    public boolean addUsers(Users users){
        if (usersService.addUsers(users)){
            return true;
        }else {
            return false;
        }
    }
    @RequestMapping("getAUsers")
    @ResponseBody
    public Users getAUsers(Integer id){
        Users aUsers = usersService.getAUsers(id);
        return aUsers;
    }
    @RequestMapping("updateUsers")
    @ResponseBody
    public boolean updateUsers(Users users){
        if (usersService.updateUsers(users)){
            return true;
        }else {
            return false;
        }
    }
}
