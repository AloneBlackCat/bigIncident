package com.zpq.bigincident.controller;

import com.zpq.bigincident.pojo.Result;
import com.zpq.bigincident.pojo.TenCentCosProperties;
import com.zpq.bigincident.utils.TenXunCosUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private TenCentCosProperties tenCentCosProperties;

    @PostMapping
    public <T> Result<T> upload(MultipartFile file){
        /**
         * String filename = file.getOriginalFilename();
         *         // 保证文件名称的唯一,防止文件覆盖
         *         assert filename != null;
         *         String newFileName = UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
         *         file.transferTo(new File("E:\\BaiduNetdiskDownload\\"+newFileName));
         *
         */
        String url = TenXunCosUtil.UploadCos(tenCentCosProperties, file);
        return Result.success(url);
    }
}
