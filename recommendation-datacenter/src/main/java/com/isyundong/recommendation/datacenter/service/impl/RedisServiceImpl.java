package com.isyundong.recommendation.datacenter.service.impl;

import com.isyundong.recommendation.datacenter.config.redis.RedisTemplateConfig;
import com.isyundong.recommendation.datacenter.service.RedisService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
@AllArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final RedisTemplateConfig redisTemplate;

    @Override
    public String get(String key) {
        for (RedisTemplate<String, ?> template : redisTemplate.getRedisTemplates()) {
            Object result = template.opsForValue()
                    .get(key);
            if (!ObjectUtils.isEmpty(result)) {
                return (String) result;
            }
        }
        return null;
    }
}
