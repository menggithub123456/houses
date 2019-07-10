package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.service.DistrictService;
import com.team.house.util.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class DistrictController{
    @Autowired
    private DistrictService districtService;

    @RequestMapping("getDistrict")
    @ResponseBody
    public Map<String,Object> getDistrict(PageParam pageParam){
        PageInfo<District> info = districtService.getDistrictByPage(pageParam);
        Map<String,Object> map=new HashMap<>();
        map.put("total",info.getTotal());
        map.put("rows",info.getList());
        return map;
    }
    @RequestMapping("insertDistrict")
    @ResponseBody
    public boolean insertDistrict(District district){
        if (districtService.insertDistrict(district)){
            return true;
        }else {
            return false;
        }
    }
    @RequestMapping("getADistrict")
    @ResponseBody
    public District getADistrict(Integer id){
        District district = districtService.getDistrict(id);
        return district;
    }
    @RequestMapping("updateDistrict")
    @ResponseBody
    public boolean updateDistrict(District district){
        if (districtService.updateDistrict(district)){
            return true;
        }else {
            return false;
        }
    }
    @RequestMapping("delDistrictList")//批量删除方法一
    @ResponseBody
    public boolean delDistrictList(Integer[] id){
        for (Integer i:id) {
            System.out.println(i);
        }
        if (districtService.delDistrictList(id)){
            return true;
        }else {
            return false;
        }
    }
    @RequestMapping("delMoreDistrict")//批量删除方法二
    @ResponseBody
    public boolean delMoreDistrict(Integer[] id){
        if (districtService.delMoreDistrict(id)){
            return true;
        }else {
            return false;
        }
    }
    @RequestMapping("delDistrict")
    @ResponseBody
    public boolean delDistrict(Integer id){
        if (districtService.delDistrict(id)){
            return true;
        }else {
            return false;
        }
    }
}
