package com.isyundong.recommendation.toolbox.openSearch.base;

import com.isyundong.recommendation.toolbox.exception.RecommendationException;
import lombok.Getter;

public interface Query {
    String toDsl() throws RecommendationException;
    @Getter
    enum QueryType {
        MUST("must"), MUST_NOT("must_not"), FILTER("filter"), SHOULD("should"),
        ;
        private final String type;
        QueryType(String type) {
            this.type = type;
        }
    }
}
