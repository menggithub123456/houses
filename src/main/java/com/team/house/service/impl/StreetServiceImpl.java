package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.entity.StreetExample;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.StreetService;
import com.team.house.util.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;
    @Override
    public PageInfo<Street> getStreetList(Integer id,PageParam pageParam) {
        PageHelper.startPage(pageParam.getPage(),pageParam.getRows());
        StreetExample example=new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictIdEqualTo(id);//通过区域编号查询当前街道
        List<Street> streets = streetMapper.selectByExample(example);
        return new PageInfo<Street>(streets);
    }

    @Override
    public boolean addStreet(Street street) {
        return streetMapper.insertSelective(street)>0?true:false;
    }

    @Override
    public boolean delAStreet(Integer id) {
        return streetMapper.deleteByPrimaryKey(id)>0?true:false;
    }

    @Override
    public List<Street> getAllStreetByDid(Integer id) {
        StreetExample example=new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictIdEqualTo(id);//通过区域编号查询当前街道
        return streetMapper.selectByExample(example);
    }
}
