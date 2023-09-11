package com.huifeng.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : yyh
 * @create : 2022-10-08 - 0:07
 * @describe:
 */
@Configuration
@ConfigurationProperties("emq")
@Data
public class EmqConfig{
    private String mqttServerUrl;
}
