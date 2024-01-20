package com.isyundong.recommendation.toolbox.openSearch.param.domain;

import java.io.Serializable;

public record BaseSort(String field, Sort.OrderType orderType) implements Serializable {

    public static BaseSort build(String field){
        return build(field, Sort.OrderType.DESC);
    }
    public static BaseSort build(String field,Sort.OrderType type){
        return new BaseSort(field,type);
    }
}
