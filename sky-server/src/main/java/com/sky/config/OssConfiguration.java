package com.sky.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sky.properties.AliOssProperties;
import com.sky.utils.AliOssUtil;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class OssConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public AliOssUtil aliOssUtil(AliOssProperties prop) {
        log.info("开始创建阿里云文件上传工具类对象：{}", prop);
        return new AliOssUtil(prop.getEndpoint(),
                              prop.getAccessKeyId(),
                              prop.getAccessKeySecret(),
                              prop.getBucketName());
    }
}
