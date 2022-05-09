package com.lky.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.lky.project.response.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class UploadDownController {

    @Value("${upload.photo.sufix}")
    private String uploadPhotoSufix;

    @Value("${upload.photo.maxsize}")
    private long uploadPhotoMaxSize;

    @Value("${upload.photo.path}")
    private String uploadPhotoPath;//文件保存位置

    /**
     * 文件上传
     * @param picture
     * @param request
     * @return
     */
    @RequestMapping("/upload")
    public Result upload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request) {
        System.out.println("进入upload");

        //获取文件在服务器的储存位置
        /*String path ="C:/Users/Administrator/Desktop/毕业设计项目/代码/project/upload";*/

        String path ="E:\\BigdataProjects2019\\vue-project\\public\\upload";
        File filePath = new File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }

        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);

        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = name +"_"+ date + "." + type;
        System.out.println("新文件名称：" + fileName);

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);

        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            System.out.println("上传成功");
            //将文件在服务器的存储路径返回
            return new Result().setCode(200).setMessage("/upload/" + fileName);
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return new Result().setCode(200).setMessage("/upload/" + fileName);
        }
    }




    /**
     * 图片统一上传类
     * @param photo
     * @return
     */
    @RequestMapping(value="/upload_photo",method= RequestMethod.POST)
    @ResponseBody
    public String uploadPhoto(@RequestParam(name="photo",required=true) MultipartFile photo) throws IOException {
        JSONObject jsonObject = new JSONObject();
        //判断文件类型是否是图片
        String originalFilename = photo.getOriginalFilename();
        //获取文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
        if(!uploadPhotoSufix.contains(suffix.toLowerCase())){
            jsonObject.put("code","500");
            jsonObject.put("msg","图片格式不正确");
            return jsonObject.toString();
        }
        if(photo.getSize()/1024 > uploadPhotoMaxSize){
            jsonObject.put("code","500");
            jsonObject.put("msg","图片大小不能超过" + (uploadPhotoMaxSize/1024) + "M");
            return jsonObject.toString();
        }
        File file = new File("");
        String filePath1 = file.getCanonicalPath();
        String s = StringUtils.substringBeforeLast(filePath1, "\\");
        String uploadPhotoPath1=s+uploadPhotoPath;
        //准备保存文件
        File filePath = new File(uploadPhotoPath1);
        if(!filePath.exists()){
            //若不存在文件夹，则创建一个文件夹
            filePath.mkdir();
        }
        String filename = System.currentTimeMillis() + suffix;
        try {
            photo.transferTo(new File(uploadPhotoPath1+filename));
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("图片上传成功，保存位置：" + uploadPhotoPath1 + filename);
        jsonObject.put("code","200");
        jsonObject.put("msg","上传成功");
        jsonObject.put("filename","img/"+filename);
        return jsonObject.toString();
    }
}
