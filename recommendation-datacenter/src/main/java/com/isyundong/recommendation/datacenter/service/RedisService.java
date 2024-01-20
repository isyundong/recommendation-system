package com.isyundong.recommendation.datacenter.service;

public interface RedisService {

    /**
     * 根据 Key 获取数据
     */
    String get(String key);

}
