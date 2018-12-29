package com.lyloou.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

@SpringBootApplication
@MapperScan(
        basePackages = "com.lyloou.demo.*",
        annotationClass = MapperScan.class
)
public class DemoApplication {
    // 注入RedisTemplate
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate = null;

    // 使用注解@PostConstruct，使得Spring IoC容器在装配当前Bean的时候，调度这个方法
    @PostConstruct
    public void init() {
        // 获取字符串序列化器
        RedisSerializer<String> strSerializer = redisTemplate.getStringSerializer();
        // 设置Redis键（key）序列化器
        redisTemplate.setKeySerializer(strSerializer);
        // 设置Redis散列结构字段（field）序列化器
        redisTemplate.setHashKeySerializer(strSerializer);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
