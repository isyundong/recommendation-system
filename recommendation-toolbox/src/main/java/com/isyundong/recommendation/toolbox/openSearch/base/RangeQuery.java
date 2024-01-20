package com.isyundong.recommendation.toolbox.openSearch.base;

import com.isyundong.recommendation.toolbox.ValidateUtil;
import com.isyundong.recommendation.toolbox.exception.RecommendationException;
import lombok.Getter;

import java.util.Locale;

public record RangeQuery(String field, RangeType type, float val) implements Query {
    @Getter
    public enum RangeType {
        GTE("gte"),
        LTE("lte"),
        GT("gt"),
        LT("lt"),
        ;

        private final String type;

        RangeType(String type) {
            this.type = type;
        }
    }

    private static final String textBlock = """
                    "range":{"%s":{"%s":%f}}
            """;

    @Override
    public String toDsl() throws RecommendationException {
        ValidateUtil.nonBlank(this.field, "range query field is not exist");
        ValidateUtil.nonNull(this.val, "range query val is not exist");
        ValidateUtil.nonNull(this.type, "range query type is not exist");
        return String.format(Locale.US, textBlock, this.field, this.type.getType(), this.val);
    }
}
