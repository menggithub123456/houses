package com.team.house.mapper;

import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;
import java.util.List;

public interface DistrictMapper {
    int deleteByExample(DistrictExample example);

    int delMoreDistrict(Integer[] ids);

    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);
}