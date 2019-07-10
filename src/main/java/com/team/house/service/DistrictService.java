package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.util.PageParam;

import java.util.List;

public interface DistrictService {
    public PageInfo<District> getDistrictByPage(PageParam pageParam);
    public boolean insertDistrict(District district);
    public District getDistrict(Integer id);
    public boolean updateDistrict(District district);
    public boolean delDistrict(Integer id);
    public boolean delDistrictList(Integer[] id);//批量删除一
    //实现批量删除:使用in关键这同时删除多条
    //在DistrictMapper.xml中添加方法
    public boolean delMoreDistrict(Integer[] id);//批量删除二
    //查询所以的区域
    public List<District> getAllDistrict();
}
