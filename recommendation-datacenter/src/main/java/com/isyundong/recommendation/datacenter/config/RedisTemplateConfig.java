package com.isyundong.recommendation.datacenter.config;


import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class RedisTemplateConfig {

    private final RedisConfig redisConfig;

    private final List<RedisTemplate<String, ?>> redisTemplates;

    @PostConstruct
    public synchronized void buildRedisTemplates() {
        if (!CollectionUtils.isEmpty(redisTemplates)) {
            return;
        }
        for (RedisConfig.RedisProperties redisProperties : redisConfig.getConfigs()) {

            RedisTemplate<String, ?> redisTemplate = new RedisTemplate<>();

            redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
//            redisTemplate.setValueSerializer(stringRedisSerializer);
            redisTemplate.setHashKeySerializer(redisTemplate.getStringSerializer());
            redisTemplate.setHashValueSerializer(redisTemplate.getStringSerializer());
            redisTemplate.setConnectionFactory(buildConnectFactory(redisProperties));

            redisTemplates.add(redisTemplate);
        }
    }


    private JedisConnectionFactory buildConnectFactory(RedisConfig.RedisProperties redisProperties) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
//            poolConfig.setMaxTotal(maxActive);
//            poolConfig.setMaxIdle(maxIdle);
//            poolConfig.setMaxWait(maxWait);
//            poolConfig.setMinIdle(minIdle);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(false);
        poolConfig.setTestWhileIdle(true);
        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
                .usePooling()
                .poolConfig(poolConfig)
                .and()
                .readTimeout(Duration.ofMillis(redisProperties.getTimeout()))
                .build();

        // 单点redis
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        // 哨兵redis
        // RedisSentinelConfiguration redisConfig = new RedisSentinelConfiguration();
        // 集群redis
        // RedisClusterConfiguration redisConfig = new RedisClusterConfiguration();
        redisConfig.setHostName(redisProperties.getHost());
        redisConfig.setPort(redisProperties.getPort());
        redisConfig.setPassword(RedisPassword.of(redisProperties.getPassword()));
        redisConfig.setDatabase(Integer.parseInt(redisProperties.getDatabase()));


        return new JedisConnectionFactory(redisConfig, clientConfig);
    }


    public List<RedisTemplate<String, ?>> getRedisTemplates() {
        if (redisTemplates.isEmpty()) {
            buildRedisTemplates();
        }
        return redisTemplates;
    }


}
