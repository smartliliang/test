package com.test.controller;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by ASUS on 2019/1/14.
 */
@Controller
public class QRCodeServlet extends HttpServlet {

    private static final long serialVersionUID = 1357779219336485986L;

    @Override
    @RequestMapping("/er")
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

       /* String qrtext = request.getParameter("qrtext");*/

        ByteArrayOutputStream out = QRCode.from("http://qbpg8y.natappfree.cc/index.html").to(ImageType.PNG).stream();

        response.setContentType("image/png");
        response.setContentLength(out.size());

        OutputStream outStream = response.getOutputStream();

        outStream.write(out.toByteArray());

        outStream.flush();
        outStream.close();
    }



}
