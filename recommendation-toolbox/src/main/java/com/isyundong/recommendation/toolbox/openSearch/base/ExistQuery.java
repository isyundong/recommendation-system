package com.isyundong.recommendation.toolbox.openSearch.base;

import com.isyundong.recommendation.toolbox.ValidateUtil;
import com.isyundong.recommendation.toolbox.exception.RecommendationException;

public record ExistQuery(String field) implements Query {

    private static final String textBlock = """
                    "exists":{"field":"%s"}
            """;

    @Override
    public String toDsl() throws RecommendationException {
        ValidateUtil.nonBlank(this.field, "exist query field is not exist");
        return String.format(textBlock, this.field);
    }

}
