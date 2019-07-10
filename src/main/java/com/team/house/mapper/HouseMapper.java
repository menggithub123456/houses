package com.team.house.mapper;


import com.team.house.entity.House;
import com.team.house.entity.HouseExample;
import com.team.house.util.ListParam;
import com.team.house.util.passParam;

import java.util.List;

public interface HouseMapper {
    int deleteByExample(HouseExample example);

    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
    //显示房屋信息，连表查询
    public List<House> getHouseByUserid(Integer id);
    //修改信息。连表查询一条信息回显数据
    public House getAHouse(String id);
    //通过是否审核查看房屋信息
    public List<House> getHouseByIsPass(passParam passParam);
    //分页显示所有的未删除，已审核的房屋信息(list.jsp)
    public List<House> getAllHouses(ListParam listParam);
}