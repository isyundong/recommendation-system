package com.isyundong.recommendation.toolbox;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.isyundong.recommendation.toolbox.exception.RecommendationException;
import com.isyundong.recommendation.toolbox.openSearch.wrapper.OpenSearchQueryWrapper;
import com.isyundong.recommendation.toolbox.openSearch.annotations.OpenSearchIndex;
import com.isyundong.recommendation.toolbox.openSearch.param.Param;
import com.isyundong.recommendation.toolbox.openSearch.response.SearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.opensearch.client.Request;
import org.opensearch.client.RestClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
public class OpenSearchUtil {

    private OpenSearchUtil() {
    }

    public static String toQuery(Param<?>... paramList) {
        checkParam(paramList);
        return STR."{\{Strings.join(Stream.of(paramList)
                .filter(t -> Objects.nonNull(t.getParam()))
                .map(OpenSearchUtil::buildQuery)
                .filter(Objects::nonNull)
                .toList(), ',')}}";
    }


    /**
     * send request to es search. you can see some sample in OpenSearchAbstractQueryWrapper
     *
     */
    public static <T extends Serializable> SearchResponse<T> search(Request request, RestClient client, Class<T> domain) throws IOException {
        return JSON.parseObject(content2Str(client.performRequest(request).getEntity().getContent()), new TypeReference<SearchResponse<T>>(domain) {});
    }

    public static <TDocument> String indexName(Class<TDocument> indexClass) {
        return indexClass.getAnnotation(OpenSearchIndex.class).indexName();
    }

    /**
     * @return The index name
     */
    public static String indexNameFrom(OpenSearchQueryWrapper<?> wrapper) {
        if (StringUtils.isNotBlank(wrapper.getIndex())) {
            return wrapper.getIndex();
        } else {
            return indexName(wrapper.getIndexClass());
        }
    }

    public static String buildQuery(Param<?> param) {
        if (Objects.nonNull(param)) {
            return (STR."\"\{param.paramField()}\":\{param.toQueryDsl()}");
        }
        return null;
    }

    private static void checkParam(Param<?>... paramList) {
        int size = Stream.of(paramList)
                .map(Param::paramField)
                .distinct().toList().size();
        ValidateUtil.isTure(size == paramList.length, "duplicate parameter exists");
    }


    private static String content2Str(InputStream content) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = content.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString();
        } catch (IOException e) {
            throw new RecommendationException("content2Str fail", e);
        }
    }

}
