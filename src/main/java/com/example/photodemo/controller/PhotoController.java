package com.example.photodemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RestController
public class PhotoController {

    @PostMapping("/photo")
    public String photo(HttpServletRequest request) throws IOException {
        String imgBase64 = request.getParameter("imegse");
        contextLoads(imgBase64);
        return "success";
    }


    @GetMapping("/images")
    public List<String> getImages() throws IOException {
        String imagePath = "./img";
        List<String> images = new ArrayList<>();
        listFiles(imagePath, images);
        return images;
    }


    /**
     * Helper Method
     */
    private void listFiles(String path, List<String> images) throws IOException {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".png")) {
                byte[] fileContent = Files.readAllBytes(file.toPath());
                String imageString = Base64.getEncoder().encodeToString(fileContent);
                images.add(imageString);
            } else if (file.isDirectory()) {
                listFiles(file.getAbsolutePath(), images);
            }
        }
    }

    private void contextLoads(String base64) throws IOException {
        //判断是否有逗号 如果有就截取后半部分
        String[] split = base64.split(",");
        String s = split[1];
        byte[] imageBytes = Base64.getDecoder().decode(s);
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
        //存放位置
        String[] arr = new SimpleDateFormat("yyyyMMdd/HH_ss_mm").format(new Date()).split("/");
        File outputFile = new File("./img/"+arr[0]+"/"+arr[1]+".png");
        System.out.println(outputFile);
        outputFile.mkdirs();
        ImageIO.write(image, "png", outputFile);
    }


}
