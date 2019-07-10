package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.service.StreetService;
import com.team.house.util.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class StreetController {
    @Autowired
    private StreetService streetService;

    @RequestMapping("getStreet")
    @ResponseBody//根据区域id查找所属于它的街道，并分页显示
    public Map<String,Object> getStreet(Integer id, PageParam pageParam){
        PageInfo<Street> info = streetService.getStreetList(id, pageParam);
        Map<String,Object> map=new HashMap<>();
        map.put("total",info.getTotal());
        map.put("rows",info.getList());
        return map;
    }
    @RequestMapping("insertStreet")
    @ResponseBody
    public boolean insertStreet(Street street){
        if (streetService.addStreet(street)){
            return true;
        }else {
            return false;
        }
    }
    @RequestMapping("delAStreet")
    @ResponseBody
    public boolean delAStreet(Integer id){
        if (streetService.delAStreet(id)){
            return true;
        }else {
            return false;
        }
    }
}
