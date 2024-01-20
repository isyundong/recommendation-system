package com.isyundong.recommendation.toolbox.openSearch.param;

import com.isyundong.recommendation.toolbox.openSearch.param.domain.Sort;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SortParam extends Param<Sort> {
    private final static String sort = ("""
                {"%s":{"order":"%s"}}
            """);
    private final static String sortScipt = ("""
                 {"_script":{"script":{"source":"%s","lang":"%s"},"type":"%s","order":"%s"}}
            """);
    @Override
    public String toQueryDsl() {
        List<String> sortContext = new ArrayList<>();
        if (Objects.nonNull(param.getBaseSort())) {
            sortContext.add(String.format(sort,
                    param.getBaseSort().field(),
                    param.getBaseSort().orderType().getType()));
        }
        if (Objects.nonNull(param.getSortScript())) {
            sortContext.add(String.format(sortScipt,
                    param.getSortScript().script(),
                    param.getSortScript().lang(),
                    param.getSortScript().order(),
                    param.getSortScript().orderType().getType()));
        }
        if (CollectionUtils.isEmpty(sortContext)) {
            return null;
        }
        return "[" + Strings.join(sortContext, ',') + "]";
    }
    @Override
    public String paramField() {
        return "sort";
    }
    public static SortParam build(Sort sort) {
        SortParam sortParam = new SortParam();
        sortParam.putParam(sort);
        return sortParam;
    }
}
