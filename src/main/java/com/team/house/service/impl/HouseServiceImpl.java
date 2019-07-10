package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.entity.HouseExample;
import com.team.house.mapper.HouseMapper;
import com.team.house.service.HouseService;
import com.team.house.util.ListParam;
import com.team.house.util.PageParam;
import com.team.house.util.passParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;
    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);

    }

    @Override
    public PageInfo<House> getHouseByUserid(Integer id, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPage(),pageParam.getRows());
        List<House> list = houseMapper.getHouseByUserid(id);
        return new PageInfo<House>(list);
    }

    @Override
    public House getaHouse(String id) {
        House h = houseMapper.getAHouse(id);
        return h;
    }

    @Override
    public int updateHouse(House house) {
        int i = houseMapper.updateByPrimaryKeySelective(house);
        return i;
    }

    @Override
    public int delHouse(String id, Integer isdel) {
        House house=new House();
        house.setId(id);
        house.setIsdel(isdel);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getHouseByIsPass(Integer ispass, passParam passParam) {
        PageHelper.startPage(passParam.getPage(),passParam.getRows());
        passParam.setIspass(ispass);
        List<House> house = houseMapper.getHouseByIsPass(passParam);
        return new PageInfo<House>(house);
    }

    @Override
    public int HouseIsPass(String id, Integer ispass) {
        House house=new House();
        house.setId(id);
        house.setIspass(ispass);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getAllHouses(ListParam listParam) {
        PageHelper.startPage(listParam.getPage(),listParam.getRows());//分页
        List<House> list = houseMapper.getAllHouses(listParam);
        return new PageInfo<House>(list);
    }
}
