package com.test.service;

import com.test.bean.Message;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * Created by ASUS on 2019/1/10.
 */
public interface ExcelSer {
     void importExcelInfo(InputStream in, MultipartFile file) throws Exception;
     Integer login(String name);
     List<Message> getTmp();
     List<Message> getAll();
     Message getMessageByPicture(String images);
}
