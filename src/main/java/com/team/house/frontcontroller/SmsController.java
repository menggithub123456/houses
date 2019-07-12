package com.team.house.frontcontroller;

import com.team.house.sms.SmsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/")
public class SmsController {

    @RequestMapping("getSms")
    @ResponseBody
    public String getSms(String phone, HttpSession session){
        //1.生成验证码:生成 五位
        String code=(int)(Math.random()*100000)+"";
        //2.发送短信
        String sendMsg="验证码是:"+code+",请在120秒内输入验证码";
        int result = SmsUtil.smsload(phone, sendMsg);
        System.out.println(result);
        session.setAttribute("savecode",code);//保存验证码
        session.setMaxInactiveInterval(120);//秒
        return "{\"result\":"+result+"}";
    }
}
