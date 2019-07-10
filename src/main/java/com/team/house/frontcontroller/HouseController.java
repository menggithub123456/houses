package com.team.house.frontcontroller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.*;
import com.team.house.service.DistrictService;
import com.team.house.service.HouseService;
import com.team.house.service.StreetService;
import com.team.house.service.TypeService;
import com.team.house.util.ListParam;
import com.team.house.util.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/page/")
public class HouseController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private StreetService streetService;
    @Autowired
    private HouseService houseService;

    //加载房屋类型和区域
    @RequestMapping("goFabu")
    public String goFabu(Model model){
        List<Type> allType = typeService.getAllType();
        List<District> allDistrict = districtService.getAllDistrict();
        model.addAttribute("allType",allType);
        model.addAttribute("allDistrict",allDistrict);
        return "fabu";
    }
    //区域，街道二级联动
    @RequestMapping("getStreetbydid")
    @ResponseBody
    public List<Street> getStreetbydid(Integer id){
        List<Street> list = streetService.getAllStreetByDid(id);
        return list;
    }
    //添加房屋信息
    @RequestMapping("addHouse")
    public String addHouse(House house,
                           HttpSession session,
                           @RequestParam(name = "pfile",required = false)CommonsMultipartFile pfile)
    throws Exception{
          //1.实现图片上传：图片在图片服务器f:/images
        String filename = pfile.getOriginalFilename();
        //上传文件名称
        String expname = filename.substring(filename.lastIndexOf("."));//上传文件的扩展名
        String saveFilename=System.currentTimeMillis()+expname;//保存文件名称
        String path="f:/images/"+saveFilename;//保存路径

        System.out.println(saveFilename);
        System.out.println(path);

        File saveFile=new File(path);
        pfile.transferTo(saveFile);//上传文件
        //2.将输入的数据保存到数据库中
        house.setId(System.currentTimeMillis()+"");//设置ID
        Users user=(Users)session.getAttribute("loginInfo");
        house.setUserId(user.getId());//设置用户ID
        house.setPath(saveFilename);//设置图片名称
        house.setIspass(0);//如果数据有默认值可不设(审核)
        house.setIsdel(0);//如果数据有默认值可不设（删除）
        if (houseService.addHouse(house)>0){
            return "redirect:showMoreHouse";
        }else {
            saveFile.delete();//删除文件
            return "redirect:goFabu";
        }
    }
//分页显示房屋信息
    @RequestMapping("showMoreHouse")
    public String showMoreHouse(HttpSession session, PageParam pageParam,Model model){
        Integer id=((Users)session.getAttribute("loginInfo")).getId();
        if (pageParam.getPage()==null){
            pageParam.setPage(1);
        }
        pageParam.setRows(3);
        PageInfo<House> info = houseService.getHouseByUserid(id, pageParam);
        model.addAttribute("info",info);
        return "guanli";
    }
    //异步方式加载房屋类型
    @RequestMapping("getTypeddd")
    @ResponseBody
    public List<Type> getTypeddd(){
        List<Type> allType = typeService.getAllType();
        return allType;
    }
    //异步方式加载区域类型
    @RequestMapping("getDistrictddd")
    @ResponseBody
    public List<District> getDistrictddd(){
        List<District> allDistrict = districtService.getAllDistrict();
        return allDistrict;
    }
    //数据回显
    @RequestMapping("huixian")
    public String huixian(String id,Model model){
        House house = houseService.getaHouse(id);
        model.addAttribute("house",house);
        return "update";
    }
    //修改
    @RequestMapping("updateHouse")
    public String updateHouse(House house,String oldPath,
                              @RequestParam(name = "pfile",required = false)CommonsMultipartFile pfile) throws Exception{
        String filename = pfile.getOriginalFilename();
        if (filename.equals("")){
            //没有选择图片，不用上传新图片，数据不用更新
        }else {
            //上传图片，数据库更新 删除原图
            //1.实现图片上传：图片在图片服务器 f:/images
            String expname = filename.substring(filename.lastIndexOf("."));//文件扩展名
            String saveFilename=System.currentTimeMillis()+expname;
            String path="f:/images/"+saveFilename;
            File saveFile=new File(path);
            pfile.transferTo(saveFile);//上传新图
            new File("f:/images/"+oldPath).delete();//删除原图
            house.setPath(saveFilename);//设置新图片
        }
        //更新数据库
        int i = houseService.updateHouse(house);
        return "redirect:showMoreHouse";
    }
    //逻辑删除
    @RequestMapping("delHouse")
     public String delHouse(String id){
        houseService.delHouse(id, 1);
        return "redirect:showMoreHouse";
    }
    //list页面
    @RequestMapping("getAllHouses")
    public String getAllHouses(ListParam listParam,Model model){
        if (listParam.getPage()==null){
            listParam.setPage(1);
        }
        if (listParam.getRows()==null){
            listParam.setRows(3);
        }
        PageInfo<House> info = houseService.getAllHouses(listParam);
        model.addAttribute("info",info);
        model.addAttribute("param",listParam);
        return "list";
    }
    @RequestMapping("detail")
    public String detail(String id,Model model){
        House house = houseService.getaHouse(id);
        model.addAttribute("house",house);
        return "details";
    }
}
