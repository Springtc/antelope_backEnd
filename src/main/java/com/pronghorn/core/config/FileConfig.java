package com.pronghorn.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "file")
@Component
public class FileConfig {

    // 附件根路径
    private String rootPath;
}
