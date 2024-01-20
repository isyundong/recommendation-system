package com.isyundong.recommendation.toolbox.openSearch.base;

import com.alibaba.fastjson.JSON;
import com.isyundong.recommendation.toolbox.ValidateUtil;
import com.isyundong.recommendation.toolbox.exception.RecommendationException;

import java.util.Collection;

public record TermsQuery<T>(String field, Collection<T> value) implements Query {
    private static final String textBlock = """
                    "terms": {"%s": %s}
            """;
    @Override
    public String toDsl() throws RecommendationException {
        ValidateUtil.nonBlank(this.field, "terms query field is not exist");
        ValidateUtil.nonNull(this.value, "terms query value is not exist");
        T firstVal = this.value.stream().findFirst().orElse(null);
        if (null != firstVal){
            if (firstVal instanceof String) {
                return String.format(textBlock, this.field, JSON.toJSONString(value));
            } else if (firstVal instanceof Number) {
                return String.format(textBlock, this.field, JSON.toJSONString(value));
            }
        }
        throw new NullPointerException("terms query value is not exist!");
    }
}
