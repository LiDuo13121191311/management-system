package com.lky.project.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${upload.photo.path}")
    private String uploadPhotoPath;//文件保存位置
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File file = new File("");
        String filePath1 = null;
        String uploadPhotoPath1="";
        try {
            filePath1 = file.getCanonicalPath();
            String s = StringUtils.substringBeforeLast(filePath1, "\\");
            uploadPhotoPath1=s+uploadPhotoPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:"+uploadPhotoPath1);
    }
}
