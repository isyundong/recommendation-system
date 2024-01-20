package com.isyundong.recommendation.toolbox.openSearch.param;

import com.alibaba.fastjson.JSON;
import com.isyundong.recommendation.toolbox.openSearch.param.domain.Source;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class SourceParam extends Param<Source> {
    @Override
    public String toQueryDsl() {
        List<String> paramList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(param.getExcludes())) {
            paramList.add("\"" + "excludes" + "\":" + JSON.toJSONString(param.getExcludes()));
        }
        if (!CollectionUtils.isEmpty(param.getIncludes())) {
            paramList.add("\"" + "includes" + "\":" + JSON.toJSONString(param.getIncludes()));
        }
        if (!CollectionUtils.isEmpty(paramList)) {
            return "{" + Strings.join(paramList, ',') + "}";
        }
        return null;
    }
    @Override
    public String paramField() {
        return "_source";
    }
    public static SourceParam build(Source source) {
        SourceParam sourceParam = new SourceParam();
        sourceParam.putParam(source);
        return sourceParam;
    }
}
