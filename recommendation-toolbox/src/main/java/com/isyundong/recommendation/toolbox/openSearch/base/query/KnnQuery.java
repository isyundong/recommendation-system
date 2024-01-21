package com.isyundong.recommendation.toolbox.openSearch.base.query;

import com.alibaba.fastjson.JSONArray;
import com.isyundong.recommendation.toolbox.ValidateUtil;
import com.isyundong.recommendation.toolbox.exception.RecommendationException;
import org.springframework.util.CollectionUtils;

import java.util.List;

public record KnnQuery(String field, List<Double> vector, Integer k) implements Query {
    private static final String textBlock = """
                "knn":{"%s":{"vector":%s,"k":%d}}
            """;
    @Override
    public String toDsl() throws RecommendationException {
        ValidateUtil.nonBlank(this.field, "knn query field is not exist");
        ValidateUtil.isFalse(CollectionUtils.isEmpty(this.vector), "knn query vector is not exist");
        ValidateUtil.nonNull(this.k, "knn query k is not exist");
        return String.format(textBlock, this.field, JSONArray.toJSONString(this.vector), this.k);
    }

}
