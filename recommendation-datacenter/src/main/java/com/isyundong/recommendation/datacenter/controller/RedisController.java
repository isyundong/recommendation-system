package com.isyundong.recommendation.datacenter.controller;

import com.isyundong.recommendation.datacenter.service.RedisService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class RedisController {

    private final RedisService redisService;

    @GetMapping("/hello")
    public String hello() {
        return redisService.get("111");
    }

}
