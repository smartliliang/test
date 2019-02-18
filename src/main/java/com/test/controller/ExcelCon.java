package com.test.controller;


import com.alibaba.fastjson.JSON;
import com.test.bean.Message;
import com.test.service.ExcelSer;
import com.test.util.IndustrySMS;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;

/**
 * Created by ASUS on 2019/1/10.
 */
@Controller
public class ExcelCon {
    @Resource
    private ExcelSer excelSer;

    @RequestMapping("/excel")
    public void importExcel(MultipartFile file) throws Exception {
        InputStream in = file.getInputStream();
        //数据导入
        System.out.print("controller");
        excelSer.importExcelInfo(in,file);
        in.close();
    }

    @RequestMapping("/login")
    public String end(String name,HttpSession session){
        if("".equals(name)){
            return "redirect:index.html?id=4";
        }
        int i=excelSer.login(name);
        /*0表示不存在此用户
        * 1表示存在此用户且未签到
        * 2表示存在此用户已经签到过*/
        switch (i){
            case 0:
               /* session.setAttribute("message","请检查您的姓名是否正确或您当前不在考勤组！");*/
                return "redirect:index.html?id=0";
            case 1:
                return "redirect:success.html?name="+name;
                /*显示用户桌号以及座位号*/
            case 2:
               /* session.setAttribute("message","您已经签到过了哦！");*/
                return "redirect:index.html?id=2";
        }
      /*  session.setAttribute("message","用户信息判断异常！");*/
        return "redirect:index.html?id=3";
    }


    @RequestMapping("/gettmp")
    @ResponseBody
    public String getTmp(){
        List<Message> list=excelSer.getTmp();
        return JSON.toJSONString(list);
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public String getAll(){
        List<Message> list=excelSer.getAll();
        return JSON.toJSONString(list);
    }

    @RequestMapping("/getMessageByPicture")
    @ResponseBody
    public String getMessageByPicture(String images){
        Message message=excelSer.getMessageByPicture(images);
        return JSON.toJSONString(message);
    }

    @RequestMapping("/confirm1")
    @ResponseBody
    public void Confirm(String phone){
        IndustrySMS.execute(phone);
    }


}
