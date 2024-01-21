package com.isyundong.recommendation.datacenter.config;

import lombok.Data;

import java.util.List;

@Data
public abstract class BaseConfig {



    @Data
    public static class BaseProperties {

        /**
         * 实例名称
         */
        private String instanceName;

    }

}
