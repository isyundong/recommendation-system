package com.isyundong.recommendation.datacenter.config.redis;

import com.isyundong.recommendation.datacenter.config.BaseConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@Component
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "redis")
public class RedisConfig extends BaseConfig {

    private List<RedisConfig.RedisProperties> instanceConfigs;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class RedisProperties extends BaseProperties {

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
