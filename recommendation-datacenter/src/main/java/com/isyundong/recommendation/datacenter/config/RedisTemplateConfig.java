package com.isyundong.recommendation.datacenter.config;


import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class RedisTemplateConfig {

    private final RedisConfig redisConfig;

    private final List<RedisTemplate<String, ?>> redisTemplates = new ArrayList<>();

    @PostConstruct
    public synchronized void buildRedisTemplates() {
        if (!CollectionUtils.isEmpty(redisTemplates)) {
            return;
        }
        for (RedisConfig.RedisProperties redisProperties : redisConfig.getConfigs()) {
            redisTemplates.add(buildRedisTemplateInstance(redisProperties));
        }
    }

    private synchronized RedisTemplate<String, ?> buildRedisTemplateInstance(RedisConfig.RedisProperties redisProperties) {
        return buildRedisTemplate(buildConnectFactory(redisProperties));
    }

    private LettuceConnectionFactory buildConnectFactory(RedisConfig.RedisProperties redisProperties) {
        LettuceConnectionFactory factory = new LettuceConnectionFactory(buildConfiguration(redisProperties), buildClientConfiguration(redisProperties));
        factory.setDatabase(Integer.parseInt(redisProperties.getDatabase()));
        factory.afterPropertiesSet();

        return factory;
    }

    private LettuceClientConfiguration buildClientConfiguration(RedisConfig.RedisProperties redisProperties) {
        return LettucePoolingClientConfiguration
                .builder()
                .commandTimeout(Duration.ofSeconds(redisProperties.getTimeout()))
//                .poolConfig(getPoolConfig(redisProperties))
                .build();
    }

    private RedisStandaloneConfiguration buildConfiguration(RedisConfig.RedisProperties redisProperties) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisProperties.getHost());
        configuration.setPort(redisProperties.getPort());
        configuration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        return configuration;
    }


    private RedisTemplate<String, ?> buildRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
        redisTemplate.setValueSerializer(redisTemplate.getStringSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    public List<RedisTemplate<String, ?>> getRedisTemplates() {
        if (redisTemplates.isEmpty()) {
            buildRedisTemplates();
        }
        return redisTemplates;
    }


}
