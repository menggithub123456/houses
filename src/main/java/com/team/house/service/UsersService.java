package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.util.UserParam;

public interface UsersService {
    public PageInfo<Users> getUserList(UserParam userParam);
    public boolean addUsers(Users users);
    public Users getAUsers(Integer id);
    public boolean updateUsers(Users users);
    //判断用户名是否存在
    public int checkUsername(String username);
    //用户登录
    public Users login(String name,String password);
}
