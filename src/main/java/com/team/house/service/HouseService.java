package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.util.ListParam;
import com.team.house.util.PageParam;
import com.team.house.util.passParam;

import java.util.List;

public interface HouseService {
    public int addHouse(House house);
    //分页显示注册用户自己的房屋信息
    public PageInfo<House> getHouseByUserid(Integer id, PageParam pageParam);
    //修改回显
    public House getaHouse(String id);
    //修改
    public int updateHouse(House house);
    //逻辑删除
    public int delHouse(String id,Integer isdel);
    //通过是否审核查看房屋信息
    public PageInfo<House> getHouseByIsPass(Integer ispass, passParam passParam);
    //修改ispass的状态
    public int HouseIsPass(String id,Integer ispass);
    //分页显示所有的未删除，已审核的房屋信息(list.jsp)
    public PageInfo<House> getAllHouses(ListParam listParam);
}
