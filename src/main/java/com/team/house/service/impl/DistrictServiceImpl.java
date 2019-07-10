package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;
import com.team.house.mapper.DistrictMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.DistrictService;
import com.team.house.util.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;
    @Override
    public PageInfo<District> getDistrictByPage(PageParam pageParam) {
        DistrictExample example=new DistrictExample();
        PageHelper.startPage(pageParam.getPage(),pageParam.getRows());
        List<District> districts = districtMapper.selectByExample(example);
        PageInfo<District> list=new PageInfo(districts);
        return list;
    }

    @Override
    public boolean insertDistrict(District district) {
        return districtMapper.insertSelective(district)>0?true:false;
    }

    @Override
    public District getDistrict(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district)>0?true:false;
    }

    @Override
    @Transactional//事务    删除单条
    public boolean delDistrict(Integer id) {
        if (streetMapper.deleteByDistrictId(id)>0&&districtMapper.deleteByPrimaryKey(id)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override//批量删除一
    public boolean delDistrictList(Integer[] id) {
        DistrictExample example=new DistrictExample();
        DistrictExample.Criteria criteria = example.createCriteria();
        List<Integer> integers = Arrays.asList(id);
        criteria.andIdIn(integers);
        int i = districtMapper.deleteByExample(example);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    @Override//批量删除二
    public boolean delMoreDistrict(Integer[] id) {
        int i = districtMapper.delMoreDistrict(id);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(null);
    }

}
