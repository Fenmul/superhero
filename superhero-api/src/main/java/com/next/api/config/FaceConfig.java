package com.next.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 头像文件的配置
 */
@Configuration
// 批量注入配置文件中的属性，类似于 Value 注解
@ConfigurationProperties(prefix = "com.next")
// 加载指定位置的配置文件
@PropertySource("classpath:file.properties")
public class FaceConfig {

    private String fileSpace;
    private String url;

    public String getFileSpace() {
        return fileSpace;
    }

    public void setFileSpace(String fileSpace) {
        this.fileSpace = fileSpace;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
