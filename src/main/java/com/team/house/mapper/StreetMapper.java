package com.team.house.mapper;

import com.team.house.entity.Street;
import com.team.house.entity.StreetExample;
import java.util.List;

public interface StreetMapper {
    int deleteByExample(StreetExample example);

    int deleteByPrimaryKey(Integer id);

    int deleteByDistrictId(Integer did);

    int insert(Street record);

    int insertSelective(Street record);

    List<Street> selectByExample(StreetExample example);

    Street selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);
}