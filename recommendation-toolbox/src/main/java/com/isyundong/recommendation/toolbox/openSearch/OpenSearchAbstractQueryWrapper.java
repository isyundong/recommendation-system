package com.isyundong.recommendation.toolbox.openSearch;


import com.isyundong.recommendation.toolbox.openSearch.base.Query;
import com.isyundong.recommendation.toolbox.openSearch.param.*;
import com.isyundong.recommendation.toolbox.openSearch.param.domain.Sort;
import com.isyundong.recommendation.toolbox.openSearch.param.domain.SortScript;
import com.isyundong.recommendation.toolbox.openSearch.param.domain.Source;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class OpenSearchAbstractQueryWrapper<T extends OpenSearchBaseIndex, P extends OpenSearchAbstractQueryWrapper<T, P>> {

    @Getter
    protected final Class<T> indexClass;

    protected final Integer from;

    protected final Integer size;

    protected final boolean trackTotalHits;

    protected final Map<Query.QueryType, List<Query>> queryTypeList;

    protected Sort sort;

    protected Source source;

    /**
     * the sources of  the index name, by priority:
     * <p>1. assign to the index value</p>
     * <p>2. OpenSearchIndex#index</p>
     *
     * @see OpenSearchUtil#indexNameFrom(OpenSearchQueryWrapper)
     */
    @Setter
    @Getter
    protected String index;

    public String buildDsl() {
        return OpenSearchUtil
                .toQuery(FromParam.build(this.from),
                        SizeParam.build(this.size),
                        TrackTotalHitsParam.build(this.trackTotalHits),
                        QueryParam.build(this.queryTypeList),
                        SourceParam.build(this.source),
                        SortParam.build(this.sort));
    }

    protected OpenSearchAbstractQueryWrapper(Class<T> index, Integer from, Integer size, boolean trackTotalHits) {
        this.indexClass = index;
        this.from = from;
        this.size = size;
        this.trackTotalHits = trackTotalHits;
        this.queryTypeList = new HashMap<>();
    }

    /**
     * @param indexName can be null
     */
    public P index(String indexName) {
        setIndex(indexName);
        return this.self();
    }

    public P indexByCountry(String country) {
        String indexPrefix = OpenSearchUtil.index(indexClass);
        setIndex(String.format(indexPrefix, country.toLowerCase()));
        return this.self();
    }

    public P query(Query.QueryType type, Query query) {
        synchronized (this) {
            List<Query> queryList = queryTypeList.getOrDefault(type, new ArrayList<>());
            queryList.add(query);
            queryTypeList.put(type, queryList);
        }
        return (P) this;
    }

    public P includes(String... fieldList) {
        this.source = Source.includes(fieldList);
        return (P) this;
    }

    public P includes(List<EFunction<T, ?>> fieldList) {
        return includes(fieldList.stream().map(EFunction::getFieldName).toList().toArray(new String[]{}));
    }

    public P sort() {

        return (P) this;
    }

    public P randomSort() {
        this.sort = Sort.build(null, SortScript.randomSort());
        return (P) this;
    }


    public P self() {
        return (P) this;
    }


}


