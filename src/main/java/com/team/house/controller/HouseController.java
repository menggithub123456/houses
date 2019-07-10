package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.service.HouseService;
import com.team.house.util.ListParam;
import com.team.house.util.PageParam;
import com.team.house.util.passParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController(value = "houseController2")
@RequestMapping("/admin/")
public class HouseController {
    @Autowired
    private HouseService houseService;

    //1代表已审核 0代表未审核
    @RequestMapping("Pass")
    public Map<String,Object> isPass(Integer ispass,passParam passParam){
        PageInfo<House> house = houseService.getHouseByIsPass(ispass, passParam);
        Map<String,Object> map=new HashMap<>();
        map.put("total",house.getTotal());
        map.put("rows",house.getList());
        return map;
    }
    @RequestMapping("isPassyesOrno")
    public String isPassyesOrno(String id,Integer ispass){
        int i = houseService.HouseIsPass(id, ispass);
        return "{\"result\":"+i+"}";
    }
}
