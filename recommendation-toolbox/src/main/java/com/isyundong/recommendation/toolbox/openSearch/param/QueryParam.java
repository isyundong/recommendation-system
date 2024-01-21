package com.isyundong.recommendation.toolbox.openSearch.param;

import com.isyundong.recommendation.toolbox.openSearch.base.query.Query;
import com.isyundong.recommendation.toolbox.openSearch.base.query.Query.QueryType;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryParam extends Param<Map<QueryType, List<Query>>> {
    @Override
    public String toQueryDsl() {
        List<String> queryContext = new ArrayList<>();
        for (Map.Entry<Query.QueryType, List<Query>> entry : param.entrySet()) {
            List<String> tmpQueryContext = new ArrayList<>();
            for (Query query : entry.getValue()) {
                tmpQueryContext.add("{" + query.toDsl() + "}");
            }
            queryContext.add("\"" + entry.getKey().getType() + "\":" + "[" + Strings.join(tmpQueryContext, ',') + "]");
        }
        return "{\"bool\":{" + Strings.join(queryContext, ',') + "}}";
    }

    @Override
    public String paramField() {
        return "query";
    }

    public static QueryParam build(Map<Query.QueryType, List<Query>> param) {
        QueryParam queryParam = new QueryParam();
        queryParam.putParam(param);
        return queryParam;
    }
}
