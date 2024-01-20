package com.isyundong.recommendation.datacenter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {

    private List<RedisProperties> configs;

    @Data
    public static class RedisProperties {

        /**
         * Redis 实例名称
         */
        private String instantName;

        /**
         * 库地址
         */
        private String database;

        /**
         * 主机地址
         */
        private String host;

        /**
         * 端口
         */
        private Integer port;

        /**
         * 密码
         */
        private String password;

        /**
         * 超时时间
         */
        private Integer timeout;

    }
}
