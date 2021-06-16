package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @auther Fanxing
 * 这是一个简介
 */


@Data
@Configuration
@ConfigurationProperties(prefix = "camera")
public class CemeraConfig {
    List<Map<String,String>> config;
}
