package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.util.PageParam;

import java.util.List;

public interface TypeService {
    public PageInfo<Type> getType(PageParam pageParam);
    public boolean insertType(Type type);
    public Type getAType(Integer id);
    public boolean updateType(Type type);
    public boolean delType(Integer id);
    public int delMoreType(List<Integer> id);//批量删除
    //查询所有的房屋类型
    public List<Type> getAllType();
}
