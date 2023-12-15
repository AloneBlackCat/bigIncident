package com.zpq.bigincident.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @ToString
public class TenCentCosProperties {
    // 用户的 SecretId
    @Value("${tenCentCos.secretId}")
    private String secretId;
    // 用户的 SecretKey
    @Value("${tenCentCos.secretKey}")
    private String secretKey;
    // COS所属地域
    @Value("${tenCentCos.cosRegion}")
    private String cosRegion;
    // 指定文件将要存放的存储桶
    @Value("${tenCentCos.bucketName}")
    private String bucketName;
    // COS访问域名
    @Value("${tenCentCos.tencentDomain}")
    private String tencentDomain;

}
