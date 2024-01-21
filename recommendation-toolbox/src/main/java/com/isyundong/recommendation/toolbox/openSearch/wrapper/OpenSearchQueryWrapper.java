package com.isyundong.recommendation.toolbox.openSearch.wrapper;

import com.isyundong.recommendation.toolbox.OpenSearchUtil;
import com.isyundong.recommendation.toolbox.funcation.ExtractNameFunction;
import com.isyundong.recommendation.toolbox.openSearch.OpenSearchBaseIndex;
import com.isyundong.recommendation.toolbox.openSearch.base.query.*;
import com.isyundong.recommendation.toolbox.openSearch.param.*;
import com.isyundong.recommendation.toolbox.openSearch.param.domain.Sort;
import com.isyundong.recommendation.toolbox.openSearch.param.domain.SortScript;
import com.isyundong.recommendation.toolbox.openSearch.param.domain.Source;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import static com.isyundong.recommendation.toolbox.openSearch.base.query.RangeQuery.RangeType.*;

@Slf4j
public class OpenSearchQueryWrapper<T extends OpenSearchBaseIndex> extends OpenSearchWrapper {

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

    /**
     * @param indexName can be null
     */
    public  OpenSearchQueryWrapper<T> index(String indexName) {
        setIndex(indexName);
        return this.self();
    }

    public OpenSearchQueryWrapper<T> indexByCountry(String country) {
        String indexPrefix = OpenSearchUtil.indexName(indexClass);
        setIndex(String.format(indexPrefix, country.toLowerCase()));
        return this.self();
    }


    public OpenSearchQueryWrapper<T> query(Query.QueryType type, Query query) {
        synchronized (this) {
            List<Query> queryList = queryTypeList.getOrDefault(type, new ArrayList<>());
            queryList.add(query);
            queryTypeList.put(type, queryList);
        }
        return this.self();
    }

    public OpenSearchQueryWrapper<T> includes(String... fieldList) {
        this.source = Source.includes(fieldList);
        return this.self();
    }

    public OpenSearchQueryWrapper<T> includes(List<ExtractNameFunction<T, ?>> fieldList) {
        return includes(fieldList.stream().map(ExtractNameFunction::getFieldName).toList().toArray(new String[]{}));
    }

    /**
     * 不建议使用会很卡
     *
     * @return
     */
    @Deprecated
    public OpenSearchQueryWrapper<T> randomSort() {
        this.sort = Sort.build(null, SortScript.randomSort());
        return this.self();
    }


    public OpenSearchQueryWrapper<T> self() {
        return this;
    }


    public OpenSearchQueryWrapper(Class<T> index, Integer from, Integer size) {
        // default set don't need track total hits
        this.indexClass = index;
        this.from = from;
        this.size = size;
        this.trackTotalHits = false;
        this.queryTypeList = new HashMap<>();
    }

    public OpenSearchQueryWrapper(Class<T> index, Integer from, Integer size, boolean trackTotalHits) {
        this.indexClass = index;
        this.from = from;
        this.size = size;
        this.trackTotalHits = trackTotalHits;
        this.queryTypeList = new HashMap<>();
    }

    public <V> OpenSearchQueryWrapper<T> term(ExtractNameFunction<T, ?> field, V val) {
        return term(field.getFieldName(), val);
    }

    public <V> OpenSearchQueryWrapper<T> term(String field, V val) {
        return query(Query.QueryType.FILTER, new TermQuery<>(field, val));
    }

    public OpenSearchQueryWrapper<T> knn(ExtractNameFunction<T, ?> field, List<Double> embedding, Integer k) {
        return knn(field.getFieldName(), embedding, k);
    }

    public OpenSearchQueryWrapper<T> knn(String field, List<Double> embedding, Integer k) {
        return query(Query.QueryType.MUST, new KnnQuery(field, embedding, k));
    }

    public OpenSearchQueryWrapper<T> gt(ExtractNameFunction<T, ?> field, float val) {
        return gt(field.getFieldName(), val);
    }

    public OpenSearchQueryWrapper<T> gt(String field, float val) {
        return query(Query.QueryType.FILTER, new RangeQuery(field, GT, val));
    }

    public OpenSearchQueryWrapper<T> gte(ExtractNameFunction<T, ?> field, float val) {
        return gte(field.getFieldName(), val);
    }

    public OpenSearchQueryWrapper<T> gte(String field, float val) {
        return query(Query.QueryType.FILTER, new RangeQuery(field, GTE, val));
    }

    public OpenSearchQueryWrapper<T> lt(ExtractNameFunction<T, ?> field, float val) {
        return lt(field.getFieldName(), val);
    }

    public OpenSearchQueryWrapper<T> lt(String field, float val) {
        return query(Query.QueryType.FILTER, new RangeQuery(field, LT, val));
    }

    public OpenSearchQueryWrapper<T> lte(ExtractNameFunction<T, ?> field, float val) {
        return lte(field.getFieldName(), val);
    }

    public OpenSearchQueryWrapper<T> lte(String field, float val) {
        return query(Query.QueryType.FILTER, new RangeQuery(field, LTE, val));
    }

    public OpenSearchQueryWrapper<T> exists(ExtractNameFunction<T, ?> field) {
        return exists(field.getFieldName());
    }

    public OpenSearchQueryWrapper<T> exists(String field) {
        return query(Query.QueryType.FILTER, new ExistQuery(field));
    }

    public OpenSearchQueryWrapper<T> terms(ExtractNameFunction<T, ?> field, Collection<?> vales) {
        return terms(field.getFieldName(), vales);
    }

    public OpenSearchQueryWrapper<T> terms(String field, Collection<?> vales) {
        return query(Query.QueryType.FILTER, new TermsQuery<>(field, vales));
    }

    public <K> OpenSearchQueryWrapper<T> terms(ExtractNameFunction<T, ?> field, K... vales) {
        return terms(field, List.of(vales));
    }

}
