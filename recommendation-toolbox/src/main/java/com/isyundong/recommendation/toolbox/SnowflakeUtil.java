package com.isyundong.recommendation.toolbox;


import java.util.UUID;

public class SnowflakeUtil {

    public static String randomSnowflake() {
        return UUID.randomUUID().toString();
    }

}
