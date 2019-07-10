package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.util.PageParam;
import com.team.house.util.UserParam;

import java.util.List;

public interface StreetService {
    public PageInfo<Street> getStreetList(Integer id,PageParam pageParam);
    public boolean addStreet(Street street);
    public boolean delAStreet(Integer id);
    public List<Street> getAllStreetByDid(Integer id);
}
