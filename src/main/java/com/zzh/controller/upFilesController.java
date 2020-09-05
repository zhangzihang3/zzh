package com.zzh.controller;

import com.zzh.service.fileI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Collection;
import java.util.Map;

@RestController
public class upFilesController {
    @Autowired
    com.zzh.service.fileI fileI;

    /**
     * @param
     * @return
     * @function 上传单个文件
     */
    @PostMapping("/upFileOne")
    public String upFile(@RequestParam("file") MultipartFile multipartFile) {
        Boolean aBoolean = fileI.saveFile(multipartFile);
        return "upFileOneHtml";
    }

    /**
     * @param
     * @return
     * @function 上传多个文件
     */
    @PostMapping("/upFileMany")
    public String upFile(HttpServletRequest request) {
        //1:把请求转换为，多文件请求
        MultipartRequest multipartRequest = (MultipartRequest) request;
        //2：从中获取多文件上传的fileMap
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        //3：获取多文件
        Collection<MultipartFile> multipartFiles = fileMap.values();
        for (MultipartFile file : multipartFiles) {
            fileI.saveFile(file);
        }
        return "upFileManyHtml";
    }

}
