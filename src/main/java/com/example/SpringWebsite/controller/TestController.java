package com.example.SpringWebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class TestController {
    @PostMapping("/test")
    public String postTest(@RequestParam("image") MultipartFile image) throws IOException {
        System.out.println("POST TEST");


//        Path path = Paths.get("/uploads");
        String uploadPath = "/WebImg";
        Path path = Paths.get(uploadPath);
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }
        if(image.isEmpty()){
            return "index";
        }
        try{
            InputStream inputStream = image.getInputStream();
            Files.copy(inputStream,path.resolve(image.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/test";
    }

    @GetMapping("/test")
    public String Test(){
        System.out.println("GET TEST");
        Path bookCoverDir = Paths.get("./BookCover");
        String coverPath = bookCoverDir.toFile().getAbsolutePath();
        System.out.println(coverPath);
        System.out.println(".\\BookCover");
        return "test";
    }

//    public String postTest(@RequestParam("image") MultipartFile image){
//        System.out.println("POST TEST");
//
////        Path path = Paths.get("/uploads");
//        Path path = Paths.get("E:\\WebImg");
//        if(image.isEmpty()){
//            return "index";
//        }
//        try{
//            InputStream inputStream = image.getInputStream();
//            Files.copy(inputStream,path.resolve(image.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return "/test";
//    }
}
