package com.test.mapper;


import com.test.bean.Message;
import com.test.bean.Tabletwo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ASUS on 2019/1/10.
 */
@Mapper
@Component(value = "excelMapper")
public interface ExcelMapper {
    void insertExcel(@Param(value = "list") List<Tabletwo> list);
    List<Message> login(String name);
    void changestatus(int id);
    List<Message> getTmp();
    void changetmp(int id);
    List<Message> getAll();
    Message getMessageByPicture(String images);
}
