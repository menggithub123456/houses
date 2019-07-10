package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.service.TypeService;
import com.team.house.util.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("getType")
    @ResponseBody
    public Map<String,Object> getType(PageParam pageParam){
        PageInfo<Type> info = typeService.getType(pageParam);
        Map<String,Object> map=new HashMap<>();
        map.put("total",info.getTotal());
        map.put("rows",info.getList());
        return map;
    }
    @RequestMapping("insertType")
    @ResponseBody
    public boolean insertType(Type type){
        if (typeService.insertType(type)){
            return true;
        }else {
            return false;
        }
    }
    @RequestMapping("getAType")
    @ResponseBody
    public Type getAType(Integer id){
        Type type = typeService.getAType(id);
        return type;
    }
    @RequestMapping("updateType")
    @ResponseBody
    public boolean updateType(Type type){
        if (typeService.updateType(type)){
            return true;
        }else {
            return false;
        }
    }
    @RequestMapping("delType")
    @ResponseBody
    public boolean delType(Integer id){
        if (typeService.delType(id)){
            return true;
        }else {
            return false;
        }
    }
    @RequestMapping("delMoreType")
    @ResponseBody
    public String delMoreType(String id){
        //分割字符串
        String[] arys = id.split(",");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arys.length; i++) {
            list.add(Integer.parseInt(arys[i]));
        }
        int i = typeService.delMoreType(list);
        return "{\"result\":" + i + "}";
    }
}
