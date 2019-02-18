package com.test.service.Impl;

import com.test.bean.Excel;
import com.test.bean.Message;
import com.test.bean.Tabletwo;
import com.test.mapper.ExcelMapper;
import com.test.service.ExcelSer;
import com.test.util.ExcelUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2019/1/10.
 */
@Service
public class Excelimpl implements ExcelSer {

    @Resource
    private ExcelMapper excelMapper;

    @Override
    public void importExcelInfo(InputStream in, MultipartFile file) throws Exception {
        System.out.print("实现类");
        List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
        List<Tabletwo> list = new ArrayList<>();
        //遍历listob数据，把数据放到List中
        for (int i = 3; i < listob.size(); i++) {
            List<Object> ob = listob.get(i);
            Tabletwo tabletwo=new Tabletwo();
            tabletwo.setName(ob.get(0).toString());
            tabletwo.setIdcard(ob.get(1).toString());
            tabletwo.setAddress(ob.get(2).toString());
            tabletwo.setCulture(ob.get(3).toString());
            tabletwo.setWorktime(ob.get(4).toString());
            tabletwo.setTelephone(ob.get(5).toString());
            tabletwo.setCompanyname(ob.get(6).toString());
            tabletwo.setCompanycode(ob.get(7).toString());
            tabletwo.setCompanyaddress(ob.get(8).toString());
            tabletwo.setCompanytelephone(ob.get(9).toString());
            tabletwo.setCompanyperson(ob.get(10).toString());
            tabletwo.setTimeofwork(ob.get(11).toString());
            tabletwo.setProfessionname(ob.get(12).toString());
            tabletwo.setWantprofession(ob.get(13).toString());
            tabletwo.setWantprofessionlevel(ob.get(14).toString());
            list.add(tabletwo);
        }
        //批量插入
        excelMapper.insertExcel(list);

    }

    @Override
    public Integer login(String name) {
        /*先比对数据库中是否有这个人的信息*/
        List<Message> list=excelMapper.login(name);
        /*如果没有直接返回false*/
        /*0表示不存在此用户
        * 1表示存在此用户且未签到
        * 2表示存在此用户已经签到过*/
        if(list.size()!=0){
           if(list.get(0).getStatus()==1){
               return 2;
           }else{
               /*修改用户的状态为1（签到状态） tmp也修改为1*/
               excelMapper.changestatus(list.get(0).getId());
               return 1;
           }
        }else{
            return 0;
        }
    }

    @Override
    public List<Message> getTmp() {
        /*先获取所有tmp为1的对象*/
        List<Message> list=excelMapper.getTmp();
        /*获取完之后要将tmp改为0*/
        if(list.size()!=0){
            for(int i=0;i<list.size();i++){
                excelMapper.changetmp(list.get(i).getId());
            }
        }

        return list;
    }

    @Override
    public List<Message> getAll() {
        List<Message> list=excelMapper.getAll();
        return list;
    }

    @Override
    public Message getMessageByPicture(String images) {
        Message message=excelMapper.getMessageByPicture(images);
        return message;
    }


}
