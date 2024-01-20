package com.isyundong.recommendation.toolbox.openSearch.param.domain;

import java.io.Serializable;

public record SortScript(String lang, String script, Sort.OrderType orderType, String order) implements
        Serializable {
    public static SortScript randomSort() {
        return new SortScript("painless", "Math.random()", Sort.OrderType.ASC, "number");
    }
}
