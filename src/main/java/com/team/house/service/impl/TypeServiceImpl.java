package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.entity.TypeExample;
import com.team.house.mapper.TypeMapper;
import com.team.house.service.TypeService;
import com.team.house.util.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public PageInfo<Type> getType(PageParam pageParam) {
        TypeExample examplel=new TypeExample();
        PageHelper.startPage(pageParam.getPage(),pageParam.getRows());
        List<Type> types = typeMapper.selectByExample(examplel);
        PageInfo<Type> list=new PageInfo<>(types);
        return list;
    }

    @Override
    public boolean insertType(Type type) {
        return typeMapper.insertSelective(type)>0?true:false;
    }

    @Override
    public Type getAType(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type)>0?true:false;
    }

    @Override
    public boolean delType(Integer id) {
        return typeMapper.deleteByPrimaryKey(id)>0?true:false;
    }

    @Override
    public int delMoreType(List<Integer> id) {
        return typeMapper.delMoreType(id);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.selectByExample(null);
    }


}
