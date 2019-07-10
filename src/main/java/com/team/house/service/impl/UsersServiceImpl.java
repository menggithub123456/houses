package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.entity.UsersExample;
import com.team.house.mapper.UsersMapper;
import com.team.house.service.UsersService;
import com.team.house.util.MD5Utils;
import com.team.house.util.UserParam;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public PageInfo<Users> getUserList(UserParam userParam) {
        PageHelper.startPage(userParam.getPage(),userParam.getRows());
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andIsadminEqualTo(1);
        if (userParam.getName()!=null){
            criteria.andNameLike("%"+userParam.getName()+"%");
        }
        if (userParam.getTelephone()!=null){
            criteria.andTelephoneLike("%"+userParam.getTelephone()+"%");
        }
        List<Users> users = usersMapper.selectByExample(example);
        return new PageInfo<Users>(users);
    }

    @Override
    public boolean addUsers(Users users) {
        return usersMapper.insertSelective(users)>0?true:false;
    }

    @Override
    public Users getAUsers(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateUsers(Users users) {
        return usersMapper.updateByPrimaryKeySelective(users)>0?true:false;
    }

    //判断用户名是否存在
    @Override
    public int checkUsername(String username) {
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(username);
        criteria.andIsadminEqualTo(0);//普通用户
        List<Users> users = usersMapper.selectByExample(example);
        return users.size();//0用户名不存在，可以注册，1用户名存在，不能注册
    }
    //用户登录
    @Override
    public Users login(String name, String password) {
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> list = usersMapper.selectByExample(example);
        if (list.size()==0){
            return null;
        }else {
            return list.get(0);
        }
    }
}
