package com.isyundong.recommendation.toolbox.openSearch.base.query;

import com.isyundong.recommendation.toolbox.ValidateUtil;
import com.isyundong.recommendation.toolbox.exception.RecommendationException;

public record TermQuery<T>(String field, T value) implements Query {
    private static final String textBlock = """
                    "term": {"%s": %s}
            """;
    @Override
    public String toDsl() throws RecommendationException {
        ValidateUtil.nonBlank(this.field, "term query field is not exist");
        ValidateUtil.nonNull(this.value, "term query value is not exist");
        if (value instanceof String) {
            return String.format(textBlock, this.field, "\"" + this.value + "\"");
        } else if (value instanceof Number) {
            return String.format(textBlock, this.field, this.value);
        }
        throw new NullPointerException("term query value is not exist!");
    }
}
