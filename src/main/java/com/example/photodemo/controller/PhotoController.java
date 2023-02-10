package com.example.photodemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@RestController
public class PhotoController {

    @PostMapping("/photo")
    public String photo(HttpServletRequest request) throws IOException {
        String imgBase64 = request.getParameter("imegse");
        contextLoads(imgBase64);
        return imgBase64;
    }


    private void contextLoads(String base64) throws IOException {
        //判断是否有逗号 如果有就截取后半部分
        String[] split = base64.split(",");
        String s = split[1];
        byte[] imageBytes = Base64.getDecoder().decode(s);
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
        File outputFile = new File("D://output.png");
        ImageIO.write(image, "png", outputFile);
    }


}
