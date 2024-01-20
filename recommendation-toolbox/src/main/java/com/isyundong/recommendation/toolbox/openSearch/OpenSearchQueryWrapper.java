package com.isyundong.recommendation.toolbox.openSearch;

import com.isyundong.recommendation.toolbox.openSearch.base.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;

import static com.isyundong.recommendation.toolbox.openSearch.base.RangeQuery.RangeType.*;

@Slf4j
public class OpenSearchQueryWrapper<T extends OpenSearchBaseIndex> extends OpenSearchAbstractQueryWrapper<T, OpenSearchQueryWrapper<T>> {

    public OpenSearchQueryWrapper(Class<T> index, Integer from, Integer size) {
        // default set don't need track total hits
        super(index, from, size, false);
    }

    public OpenSearchQueryWrapper(Class<T> index, Integer from, Integer size, boolean trackTotalHits) {
        super(index, from, size, trackTotalHits);
    }

    public <V> OpenSearchQueryWrapper<T> term(EFunction<T, ?> field, V val) {
        return term(field.getFieldName(), val);
    }

    public <V> OpenSearchQueryWrapper<T> term(String field, V val) {
        return super.query(Query.QueryType.FILTER, new TermQuery<>(field, val));
    }

    public OpenSearchQueryWrapper<T> knn(EFunction<T, ?> field, List<Double> embedding, Integer k) {
        return knn(field.getFieldName(), embedding, k);
    }

    public OpenSearchQueryWrapper<T> knn(String field, List<Double> embedding, Integer k) {
        return super.query(Query.QueryType.MUST, new KnnQuery(field, embedding, k));
    }
    public OpenSearchQueryWrapper<T> gt(EFunction<T, ?> field, float val) {
        return gt(field.getFieldName(), val);
    }
    public OpenSearchQueryWrapper<T> gt(String field, float val) {
        return super.query(Query.QueryType.FILTER, new RangeQuery(field, GT, val));
    }
    public OpenSearchQueryWrapper<T> gte(EFunction<T, ?> field, float val) {
        return gte(field.getFieldName(), val);
    }
    public OpenSearchQueryWrapper<T> gte(String field, float val) {
        return super.query(Query.QueryType.FILTER, new RangeQuery(field, GTE, val));
    }

    public OpenSearchQueryWrapper<T> lt(EFunction<T, ?> field, float val) {
        return lt(field.getFieldName(), val);
    }
    public OpenSearchQueryWrapper<T> lt(String field, float val) {
        return super.query(Query.QueryType.FILTER, new RangeQuery(field, LT, val));
    }
    public OpenSearchQueryWrapper<T> lte(EFunction<T, ?> field, float val) {
        return lte(field.getFieldName(), val);
    }
    public OpenSearchQueryWrapper<T> lte(String field, float val) {
        return super.query(Query.QueryType.FILTER, new RangeQuery(field, LTE, val));
    }


    public OpenSearchQueryWrapper<T> exists(EFunction<T, ?> field) {
        return exists(field.getFieldName());
    }
    public OpenSearchQueryWrapper<T> exists(String field) {
        return super.query(Query.QueryType.FILTER, new ExistQuery(field));
    }

    public OpenSearchQueryWrapper<T> terms(EFunction<T, ?> field, Collection<?> vales) {
        return terms(field.getFieldName(), vales);
    }

    public OpenSearchQueryWrapper<T> terms(String field, Collection<?> vales) {
        return super.query(Query.QueryType.FILTER, new TermsQuery<>(field, vales));
    }

    public <K> OpenSearchQueryWrapper<T> terms(EFunction<T, ?> field, K... vales) {
        return terms(field, List.of(vales));
    }

}
