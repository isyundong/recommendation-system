package com.isyundong.recommendation.toolbox.openSearch.param.domain;

import lombok.Data;

import java.util.List;

@Data
public class Source {
    private List<String> includes;
    private List<String> excludes;
    public static Source includes(String... fieldList) {
        Source source = new Source();
        source.setIncludes(List.of(fieldList));
        source.setExcludes(List.of());
        return source;
    }
    public static Source excludes(String... fieldList) {
        Source source = new Source();
        source.setExcludes(List.of(fieldList));
        source.setIncludes(List.of());
        return source;
    }
}
