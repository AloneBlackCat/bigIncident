package com.zpq.bigincident.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import com.zpq.bigincident.pojo.TenCentCosProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class TenXunCosUtil {

    public static String UploadCos(TenCentCosProperties tenCentCos, MultipartFile file) {
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(tenCentCos.getSecretId(), tenCentCos.getSecretKey());
        // 2 设置 bucket 的地域
        Region region = new Region(tenCentCos.getCosRegion());
        ClientConfig clientConfig = new ClientConfig(region);
        // 设置使用 https 协议
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        // 4 指定要上传的文件
        String filename = file.getOriginalFilename();
        assert filename != null;
        String suffix = filename.substring(filename.lastIndexOf("."));
        try {
            File uploadFile = File.createTempFile(filename, suffix);
            file.transferTo(uploadFile);
            // 指定文件上传到 COS 上的路径，即对象键。例如对象键为 folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
            String key = UUID.randomUUID() + suffix;
            PutObjectRequest putObjectRequest = new PutObjectRequest(tenCentCos.getBucketName(), key,uploadFile);
            cosClient.putObject(putObjectRequest);
            return tenCentCos.getTencentDomain() + key;
        } catch (Exception e) {
            return e.toString();
        } finally {
            // 关闭客户端
            cosClient.shutdown();
        }
    }
}
